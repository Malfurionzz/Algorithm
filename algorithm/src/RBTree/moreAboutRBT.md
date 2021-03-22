# 数据结构的扩张
我们通常对数据结构进行扩张来更好地完成某些特殊任务。
## 扩张方法

### 扩张步骤
1. 选取合适的基础数据结构
2. 决定附加信息（全局data or 每个节点的attribute）
3. 验证我们能够在基础数据结构中维护附加信息
4. 开发新的操作

### 扩张的红黑树定理
令f为某种附加attribute，任意节点x, x.f 仅与x, x.left, x.right的信息有关，
那么我们就可以在O（lg n）的时间复杂度下完成insert 和delete 操作,并且完成对所有节点的f的维护。

### 举例：顺序统计树（Order-Static Tree）
每个节点在红黑树的基础上增加属性size，用于记录以x为根的子树中的节点的数目（包括x）

#### 维护
##### insert()
1. 正常的RBT的插入，从根节点到插入点的路径上的节点size均+1
2. FixUp时，对x左旋/右旋仅需修改x和x的右/左节点
##### delete()
同insert()
#### 新增操作
##### select()：返回秩为i的节点（第i小的节点）
1. 递归版
```
select(OStree x,Int i)
    r=x.left.size+1
    if i==r
        return x
    else if i<r
        return select(x.left,i)
    else 
        return select(x.right,i-r)
end
```
2. 非递归版
```
select(x, i)
    r = x.left.size + 1
    while r != i
        if i < r
            x = x.left
        else x = x.right
            i = i - r
        r = x.left.size + 1
    return x
```
##### rank(): 返回某个节点的秩
1. 非递归版
```
rank(x)
    r=x.left.size+1
    y=x
    while x!=root
        if y==y.parent.right
            r+=y.parent.left.size+1
        y=y.parent
end 
```
2. 递归版
```
rank(T, x)
    if x.k == T.root.key
        return T.root.left.size + 1
    else if T.root.key > x.k
        return OS-KEY-RANK(T.left, x.k)
    else 
    return T.root.left.size + 1 + OS-KEY-RANK(T.right, x.k)
end
```
### 举例：区间树（Interval Tree）[以闭区间为例]
1. 基础结构：RBT，包括如下attribute: 一个闭区间; 关键字: 区间的左端点
2. 附加信息: x.max 以x为根的子树所有端点的最大值
3. 信息维护: x.max=max{x.left.max,x.right.max,x.h}
4. 新操作: intervalSearch(i): 在给定树中搜索与区间i重合的点

#### 新操作
##### intervalSearch
```
intervalSearch(i)
    x=root
    while x!=nil && i 与x.interval不重合
        if x.left!=nil and x.left.max >= i.low
            x=x.left
        else
            x=x.right
    return x
```