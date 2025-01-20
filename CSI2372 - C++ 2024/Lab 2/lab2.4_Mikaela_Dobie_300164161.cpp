// Mikaela Dobie 300164161
#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>
using namespace std;

int minCandies(const vector<int>& ratings) {
    int n = ratings.size();
    if (n == 0) return 0;

    vector<int> candies(n, 1);

    // Left to right pass
    for (int i = 1; i < n; ++i) {
        if (ratings[i] > ratings[i - 1]) {
            candies[i] = candies[i - 1] + 1;
        }
    }

    // Right to left pass
    for (int i = n - 2; i >= 0; --i) {
        if (ratings[i] > ratings[i + 1]) {
            candies[i] = max(candies[i], candies[i + 1] + 1);
        }
    }

    return accumulate(candies.begin(), candies.end(), 0);
}

int main() {
    vector<int> ratings = {1, 0, 2};
    cout << "Minimum candies required to give to each child: " << minCandies(ratings);

    return 0;
}
