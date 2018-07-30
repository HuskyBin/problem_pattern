// 递归算法， 如果b是0，就返回a，否则a变成b，b变成a % b
int gcd(int a, int b) {
       return (b == 0) ? a : gcd(b, a % b);
}

// 迭代算法
public int gcd(int a, int b) {
    while (b != 0 ) {
        int temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}


