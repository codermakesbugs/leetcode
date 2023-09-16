package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Println(coinChange([]int{1, 2, 5}, 11))
}



func coinChange(coins []int, amount int) int {
	if amount == 0 {
		return 0
	}

	var dp = make([]float64, amount+1)

	for i := 0; i < amount+1; i++ {
		dp[i] = 1e5
	}
	dp[0] = 0
	for i := 1; i <= amount; i++ {
		for j := 0; j < len(coins); j++ {
			if coins[j] <= i {
				fmt.Println(dp[i], 1+dp[i-coins[j]])
				dp[i] = math.Min(dp[i], 1+dp[i-coins[j]])
			}
		}
		fmt.Println(dp)
	}

	if dp[amount] == 1e5 {
		return -1
	}
	return int(dp[amount])
}
