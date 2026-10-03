Project 2 CSC 401

1. Find root to be last element of post order list
2. Find same root in inorder list
3. Divide inorder list splitting from the root
4. compare left and right lists of inorder to left and right lists of postorder
	- compare sizes and see if they match
5. If not matched, then no solution
6. Recursively repeat steps 1 - 5 on left subtree
7. Recursively repeat steps 1 - 5 on right subtree
8. Attach two resulting subtrees to the root

Algorithm:
BuildTree(I, P)

//base case
if n <- 0
   return null
if(length of I != length of P)
   return null

//find root of binary tree
root <- P[n - 1]
for i <- 0 to n-1
   if I[i] = root
	rootIndex <- i
	break
   if rootIndex = -1
	return no solution

//split inorder list
for j <- 0 to rootIndex - 1
   leftInorder[j] <- I[j]
for j <- rootIndex + 1 to n-1
   rightInorder[j - rootIndex - 1] <- I[j]


//create postorder left and right subtree lists and compare to recently made lists of inorder
leftSize <- size of leftInorder

for i <- 0 to leftSize - 1
   leftPostorder[i] <- P[i]
for i <- leftSize to n-2
   rightPostorder[i - leftSize] <- P[i]

//compare inorder and postorder lists
if set(leftInorder) does not equal set(leftPostorder)
   return no solution
if set(rightInorder) does not equal set(rightPostOrder)
   return no solution

//recursively repeat above steps for left subtree
leftChild <- BuildTree(leftInorder, leftPostorder)

//recursively repeat above steps for right subtree
rightChild <- BuildTree(rightInorder, rightPostorder)

T <- new node
T.value <- root
T.left <- leftChild
T.right <- rightChild

return T
