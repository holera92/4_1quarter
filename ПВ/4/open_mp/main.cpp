#include <iostream>
#include <omp.h>

int main()
{
    
    int n = 0, available_threads = 0;

    std::cout << "Enter number row/columns" << std::endl;
    std::cin >> n;

    std::cout << omp_get_num_procs() << " available threads" << std::endl;
    std::cout << "Choose number of threads: ";
    std::cin >> available_threads;
    std::cout << std::endl;

    omp_set_num_threads(available_threads);

    int** A = new int*[n];
    int** B = new int*[n];
    int** C = new int*[n];

    for (int i = 0; i < n; i++)
    {
        A[i] = new int[n];
        B[i] = new int[n];
        C[i] = new int[n];

        for (int j = 0; j < n; j++)
        {
            A[i][j] = random() % 10;
            B[i][j] = random() % 10;
            C[i][j] = 0;
        }
    }

    int start = omp_get_wtime();

    #pragma omp parallel for
    for (int i = 0; i < n; i++)
    {
    
        for (int j = 0; j < n; j++)
        {
            for (int k = 0; k < n; k++)
            {
                C[i][j] += A[i][k] * B[k][j];
            }
        }
    }

    std::cout << "Ellapsed " << omp_get_wtime() - start << " seconds";
    return 0;
}
