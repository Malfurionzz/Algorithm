## basic quickSort
```java
public class quickSort {
public static void innerQuickSort(int[] a, int p, int q) {
        if (p < q) {
            int r = partition(a, p, q);
            innerQuickSort(a, p, r - 1);
            innerQuickSort(a, r + 1, q);
        }
    }
}
```
主体使用了分治的方法，partition函数将首个元素定位pivot，接着定义两个标头：
* 从array尾部开始不断减小array尾的i，将首个比pivot小的元素移动到i处，
* 从array头部开始不断增大array头的j，将首个比pivot大的元素移动到j处，
* 重复以上两步直至i,j重合，此时将pivot的值赋给此位置的元素，
* 返回pivot的位置下标，作为划分
* 对pivot两边的subarray分别快排。

##关于quickSort的优化问题
快排的平均时间复杂度为O(nlgn),最坏为O(n<sup>2</sup>)
### Random Patition
快排的效率很大一部分上取决于划分的效果，如果划分时偏离中位数太多，会使两边的子数组失衡
，例如一个已经有序的数列,会导致每次子数组的长度只减少1，会退化为O(n<sup>2</sup>)的
低效情况。为此，随机选择一个数作为pivot。减少这种极端情况发生的概率。

### 尾递归优化
##### 什么是尾递归
如果一个函数中所有递归形式的调用都出现在函数的末尾，当递归调用是整个函数体中最后执行的语
句且它的返回值不属于表达式的一部分时，这个递归调用就是尾递归。尾递归函数的特点是在回归过
程中不用做任何操作，这个特性很重要，因为大多数现代的编译器会利用这种特点自动生成优化的代码。
##### 尾递归的原理
当编译器检测到一个函数调用是尾递归的时候，它就覆盖当前的活动记录而不是在栈中去创建一个新的。
编译器可以做到这点，因为递归调用是当前活跃期内最后一条待执行的语句，于是当这个调用返回时栈
帧中并没有其他事情可做，因此也就没有保存栈帧的必要了。通过覆盖当前的栈帧而不是在其之上重新
添加一个，这样所使用的栈空间就大大缩减了，这使得实际的运行效率会变得更高。

##### 实例
快排的递归函数和尾递归有些类似，但因为调用了两次自身，所以并不属于尾递归，也不会被编译器优化。
但是，我们可以手动将第二个递归改为迭代，对其进行优化，节省栈空间。为了进一步优化，我们选取递
归较短的调用，让编译器优化另一个。
```java
public class quickSort {
 public static void innerTailRecursiveQuickSort(int[] a,int p,int q){
        while (p<q) {
            int r = partition(a, p, q);
            if (r-p<q-r){
                innerTailRecursiveQuickSort(a, p, r - 1);
                p=r+1;
            }else {
                innerTailRecursiveQuickSort(a,r+1,q);
                q=r-1;
            }
        }
    }
}
```

### 改用插入排序
当快排达到一定深度后，划分的区间很小时，再使用快排的效率不高。当待排序列的长度达到一定数值后，
可以使用插入排序。
代码略。