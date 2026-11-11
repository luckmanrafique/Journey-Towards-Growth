
#!/bin/bash

pdf_escape() {
    local text="$1"

    text="${text//\\/\\\\}"
    text="${text//(/\\(}"
    text="${text//)/\\)}"

    printf '%s' "$text"
}

generate_ticket_pdf() {

    local output="$1"
    local ticket_id="$2"
    local passenger="$3"
    local phone="$4"
    local bus="$5"
    local departure="$6"
    local destination="$7"
    local journey_date="$8"
    local seats="$9"
    local fare="${10}"

    local stream=""
    local pdf=""
    local escaped
    local xref_offset
    local i

    local -a offsets

    export LC_ALL=C

    stream=$'BT\n/F1 20 Tf\n50 780 Td\n(BUS TICKET) Tj\n'
    stream+=$'/F1 11 Tf\n0 -35 Td\n'

    local -a lines=(
        "Ticket ID: $ticket_id"
        "Passenger: $passenger"
        "Phone: $phone"
        "Bus Number: $bus"
        "Departure: $departure"
        "Destination: $destination"
        "Journey Date: $journey_date"
        "Seat Number(s): $seats"
        "Total Fare: $fare"
        "Status: CONFIRMED"
    )

    for line in "${lines[@]}"; do

        escaped=$(pdf_escape "$line")

        stream+="($escaped) Tj"$'\n'
        stream+=$'0 -25 Td\n'

    done

    stream+=$'ET\n'

    pdf=$'%PDF-1.4\n'
    offsets[0]=0

    offsets[1]=${#pdf}
    pdf+=$'1 0 obj\n<< /Type /Catalog /Pages 2 0 R >>\nendobj\n'

    offsets[2]=${#pdf}
    pdf+=$'2 0 obj\n<< /Type /Pages /Kids [3 0 R] /Count 1 >>\nendobj\n'

    offsets[3]=${#pdf}
    pdf+=$'3 0 obj\n<< /Type /Page /Parent 2 0 R /MediaBox [0 0 595 842] /Resources << /Font << /F1 4 0 R >> >> /Contents 5 0 R >>\nendobj\n'

    offsets[4]=${#pdf}
    pdf+=$'4 0 obj\n<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica >>\nendobj\n'

    offsets[5]=${#pdf}
    pdf+="5 0 obj"$'\n'
    pdf+="<< /Length ${#stream} >>"$'\n'
    pdf+=$'stream\n'
    pdf+="$stream"
    pdf+=$'endstream\nendobj\n'

    xref_offset=${#pdf}

    pdf+=$'xref\n0 6\n'
    pdf+=$'0000000000 65535 f \n'

    for ((i=1; i<=5; i++)); do
        printf -v entry '%010d 00000 n \n' "${offsets[$i]}"
        pdf+="$entry"
    done

    pdf+=$'trailer\n<< /Size 6 /Root 1 0 R >>\n'
    pdf+="startxref"$'\n'
    pdf+="$xref_offset"$'\n'
    pdf+=$'%%EOF\n'

    printf '%s' "$pdf" > "$output"

    echo "PDF ticket generated: $output"
}
