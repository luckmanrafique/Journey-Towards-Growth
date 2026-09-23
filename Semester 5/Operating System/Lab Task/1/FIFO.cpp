#include <iostream>
using namespace std;
int main()
{
    int n;
    cout << "Enter number of processes: ";
    cin >> n;
    int A[n], B[n], C[n], TA[n], WT[n];
    for (int i = 0; i < n; i++)
    {
        cout << "Enter Arrival Time and Burst Time for Process "
             << i + 1 << ": ";
        cin >> A[i] >> B[i];
    }
    for (int i = 0; i < n; i++)
    {
        for (int j = i + 1; j < n; j++)
        {
            if (A[i] > A[j])
            {
                swap(A[i], A[j]);
                swap(B[i], B[j]);
            }
        }
    }
    C[0] = A[0] + B[0];
    for (int i = 1; i < n; i++)
    {
        if (C[i - 1] < A[i])
            C[i] = A[i] + B[i];
        else
            C[i] = C[i - 1] + B[i];
    }
    for (int i = 0; i < n; i++)
    {
        TA[i] = C[i] - A[i];
        WT[i] = TA[i] - B[i];
    }
    cout << "\nAT\tBT\tCT\tTA\tWT\n";
    for (int i = 0; i < n; i++)
    {
        cout << A[i] << "\t"
             << B[i] << "\t"
             << C[i] << "\t"
             << TA[i] << "\t"
             << WT[i] << endl;
    }
    return 0;
}