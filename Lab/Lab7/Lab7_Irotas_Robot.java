import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Lab7_Irotas_Robot {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;// new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);

        long c = in.nextLong();
        int number = in.nextInt();
        node head;
        if (number==0){
             head = new node(0, c, null);
        }else {
            long fk = in.nextLong();
             head = new node(fk, c, null);
            position(head, null);
        }
        node tem = head;
        for (int a = 2; a <= number; a++) {
            long fk2 = in.nextLong();
            tem = insert(tem, fk2, a, c);//先用已有序列建树
        }
        int number1 = in.nextInt();
        ArrayList<Long> output = new ArrayList<>();
        for (int a = 0; a < number1; a++) {
            String input = in.next();
            long v = 0;
            if (input.equals("rem")) {
                tem = delete(tem, in.nextInt());
            } else if (input.equals("ins")) {
                int index = in.nextInt();
                long k = in.nextLong();
                tem = insert(tem, k, index, c);
            } else if (input.equals("ask")) {
                long op = in.nextLong();
                long lb;
                long ub;
                long answer;
                lb = tem.xiajie;
                ub = tem.shangjie;
                v = op + tem.offset;
                answer = Math.max(lb, Math.min(ub, v));
                output.add(answer);
//                if (tem.offset>=0){
//                     lb=Math.max(tem.xiajie-tem.offset,0);
//                    ub =Math.max(tem.shangjie-tem.offset,0);
//                }else {
//                    lb=Math.min(tem.xiajie-tem.offset,tem.C);
//                    ub =Math.min(tem.shangjie,tem.C);
//                }
//                 if (op>=lb&&op<=ub){
//                     op=op+tem.offset;
//                     if (op>=0){
//                         output.add(Math.min(op, tem.C));
//                     }else {
//                         output.add(0L);
//                     }
//                 }else if (op<lb){
//                     op=lb+tem.offset;
//                     if (op>=0){
//                         output.add(Math.min(op, tem.C));
//                     }else {
//                         output.add(0L);
//                     }
//                 }else {
//                     op=ub+tem.offset;
//                     if (op>=0){
//                         output.add(Math.min(op, tem.C));
//                     }else {
//                         output.add(0L);
//                     }
//                 }
            }
        }
        for (int a = 0; a < output.size(); a++) {
            out.println(output.get(a));
        }
        out.close();
    }

    public static void update(node a) {//更新节点高度，子树个数以及平衡因子//不更新上下界，因为父子关系可能交错
        if (a == null) {
            return;
        } else {//高度等于子树最大高度加一，子树等于左右子树和加一
            if (a.left == null && a.right == null) {
                a.height = 0;
                a.child = 1;//维护高度和子树个数
                position(a, null);
                position1(a, null);
            } else if (a.left == null && a.right != null) {//只有右儿子
                a.height = height(a.right) + 1;
                a.child = child(a.right) + 1;
                position(a, null);
                position1(a, a.right);
            } else if (a.left != null && a.right == null) {
                a.height = height(a.left) + 1;
                a.child = child(a.left) + 1;
                position(a, a.left);
                position1(a, null);
            } else {
                a.height = Math.max(height(a.right), height(a.left)) + 1;
                a.child = child(a.right) + child(a.left) + 1;
                position(a, a.left);
                position1(a, a.right);
            }
            a.balance = height(a.left) - height(a.right);
        }

    }

    public static void position(node a, node b) {//第一步
        long b1;
        long lb1;
        long ub1;
        if (b == null) {
            b1 = 0;
            lb1 = 0;
            ub1 = a.C;
        } else {
            b1 = b.offset;
            lb1 = b.xiajie;
            ub1 = b.shangjie;//b的子区间信息
        }
        long b2 = a.key;
        long lb2 = a.lb;
        long ub2 = a.ub;


        ub1 += b2;
        lb1 += b2;
        b1 += b2;
        ub1 = Math.min(ub2, Math.max(lb2, ub1));
        lb1 = Math.min(ub2, Math.max(lb1, lb2));
//        if (b2>0){
//            b1=Math.min(b1,a.C);
//        }else {
//            b1=Math.max(b1,a.C*-1);
//        }//维护更新的offset
        a.xiajie = lb1;
        a.shangjie = ub1;
        a.offset = b1;//改变A的子树区间
    }

    public static void position1(node a, node b) {//第一步
        long b1;
        long lb1;
        long ub1;
        if (b == null) {
            b1 = 0;
            lb1 = 0;
            ub1 = a.C;
        } else {
            b1 = b.offset;
            lb1 = b.xiajie;
            ub1 = b.shangjie;//b的子区间信息
        }
        long b2 = a.offset;
        long lb2 = a.xiajie;
        long ub2 = a.shangjie;//a的子区间信息


        ub2 += b1;
        lb2 += b1;
        b2 += b1;
        ub2 = Math.min(ub1, Math.max(lb1, ub2));
        lb2 = Math.min(ub1, Math.max(lb1, lb2));

//        if (b2>0){
//            b2=Math.min(b2,a.C);
//        }else {
//            b2=Math.max(b2,a.C*-1);
//        }//维护更新的offset
        a.xiajie = lb2;
        a.shangjie = ub2;
        a.offset = b2;//改变A的子树区间
    }

    public static int height(node root) { // 避免出现空节点
        if (root != null) {
            return root.height;
        } else
            return -1;
    }

    public static long child(node root) { // 避免出现空节点
        if (root != null) {
            return root.child;
        } else
            return 0;
    }

    public static node insert(node cur, long k, int index, long C) {
        if (cur == null) {
            return cur = new node(k, C, null);
        } else {
            if (cur.left != null) {//如果有左儿子
                if (index > cur.left.child + 1) {//index大于左子树加一
//                    cur.child++;//根节点儿子加一
                    index -= (cur.left.child + 1);//减去左子树加一
                    if (cur.right != null) {//如果有右儿子
                        return insert(cur.right, k, index, C);
                    } else {
                        cur.right = new node(k, C, cur);
                        cur.right.child = 1;
                        return balanced_tree(cur);

                        //height就是0
                    }
                } else {
//                    cur.child++;//根节点儿子加一
                    return insert(cur.left, k, index, C);

                }
            } else {//如果没有左儿子
                if (index <= 1) {//左子树+1=1就是左子树不存在的情况
//                    cur.child++;//根节点儿子加一
                    cur.left = new node(k, C, cur);
                    cur.left.child = 1;
                    return balanced_tree(cur);

                    //height就是0
                } else {//要往右走
//                    cur.child++;//根节点儿子加一
                    index -= 1;//减去左子树加一
                    if (cur.right != null) {//如果有右儿子
                        return insert(cur.right, k, index, C);
                    } else {

                        cur.right = new node(k, C, cur);
                        cur.right.child = 1;
                        return balanced_tree(cur);
                        //height就是0



                    }
                }
            }
        }
    }

    public static node delete(node a, int index) {//返回根
        node cur = find_delete(a, index);
        if (cur.left == null && cur.right == null) {//删除的是叶子节点
            node tem = cur.parent;
            if (tem == null) {//树只有一个节点
                //相当于初始化
                return balanced_tree(new node(0,cur.C,null));
            } else {
                if (tem.left == cur) {
                    tem.left = null;
                } else {
                    tem.right = null;//必须这样删，不能直接把cur变成null
                }
                cur.parent = null;
                return balanced_tree(tem);
            }

        } else {//不是叶子节点
            if (cur.right != null && cur.left == null) {//只有右儿子
                node son =cur.right;
                node tem = cur.parent;
                if (tem == null) {//删除的是根
                    cur.right = null;
                    cur = son;
                    cur.parent = null;
                    return balanced_tree(cur);
                    //把他变成右儿子
                } else {
                    if (tem.left == cur) {//删除的是他爹的左儿子
                        tem.left = cur.right;

                    } else {//删除的是他爹的右儿子
                        tem.right = cur.right;

                    }
                    cur.right.parent = tem;
                    cur = null;
                    return balanced_tree(tem);
                }
            } else if (cur.right == null) {//只有左儿子
                node son =cur.left;
                node tem = cur.parent;
                if (tem == null) {//删除的是根
                    cur.left = null;
                    cur = son;
                    cur.parent = null;
                    return balanced_tree(cur);
                    //把他变成左儿子
                } else {
                    if (tem.left == cur) {//删除的是他爹的左儿子
                        tem.left = cur.left;

                    } else {//删除的是他爹的右儿子
                        tem.right = cur.left;

                    }
                    cur.left.parent = tem;
                    cur.parent = null;
                    return balanced_tree(tem);
                }
            } else {//有两个儿子
                node successor = find_hou_ji(a, index + 1);
                cur.key = successor.key;//把p的值变成后继的值
                cur.lb = successor.lb;
                cur.ub = successor.ub;

                if (successor.right == null && successor.left == null) {//后继是叶子
                    node parent = successor.parent;
                    if (parent.left == successor) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }//删除后继节点
                    successor.parent = null;
                    return balanced_tree(parent);
                } else {//后继有右儿子
                    node temp = successor.right;
                    node parent = successor.parent;
                    if (parent.left == successor) {
                        parent.left = temp;
                    } else {
                        parent.right = temp;
                    }
                    temp.parent = parent;
                    successor.parent = null;
                    return balanced_tree(temp);
                }
            }
        }
    }

    public static node find_hou_ji(node cur, int index) {
        node answer;
        if (cur == null) {
            return null;
        } else {
            if (cur.left != null) {//如果有左儿子
                if (index > cur.left.child + 1) {//index大于左子树加一
//                    cur.child--;//根节点儿子减一
                    index -= (cur.left.child + 1);//减去左子树加一
                    if (cur.right != null) {//如果有右儿子
                        return find_hou_ji(cur.right, index);
                    } else {
                        return cur;//就是这个节点
                    }
                } else if (index < cur.left.child + 1) {
//                    cur.child--;//根节点儿子减一
                    return find_hou_ji(cur.left, index);
                } else {
                    return cur;//就是这个节点
                }
            } else {//如果没有左儿子
                if (index == 1) {//左子树+1=1就是左子树不存在的情况
//                    cur.child--;//根节点儿子减一
                    return cur;//就是这个节点

                } else {//要往右走
//                    cur.child--;//根节点儿子减一
                    index -= 1;//减去左子树加一
                    if (cur.right != null) {//如果有右儿子
                        return find_hou_ji(cur.right, index);
                    } else {
                        return cur;//就是这个节点
                    }
                }
            }
        }
    }

    public static node find_delete(node cur, int index) {

        node answer;
        if (cur == null) {
            return null;
        } else {
            if (cur.left != null) {//如果有左儿子
                if (index > cur.left.child + 1) {//index大于左子树加一
//                    cur.child--;//根节点儿子减一
                    index -= (cur.left.child + 1);//减去左子树加一
                    if (cur.right != null) {//如果有右儿子
                        return find_delete(cur.right, index);
                    } else {
                        return cur;//就是这个节点
                    }
                } else if (index < cur.left.child + 1) {
//                    cur.child--;//根节点儿子减一
                    return find_delete(cur.left, index);
                } else {
                    return cur;//就是这个节点
                }
            } else {//如果没有左儿子
                if (index == 1) {//左子树+1=1就是左子树不存在的情况
//                    cur.child--;//根节点儿子减一
                    return cur;//就是这个节点

                } else {//要往右走
//                    cur.child--;//根节点儿子减一
                    index -= 1;//减去左子树加一
                    if (cur.right != null) {//如果有右儿子
                        return find_delete(cur.right, index);
                    } else {
                        return cur;//就是这个节点
                    }
                }
            }
        }
    }

    public static node balanced_tree(node cur) {//返回根
        update(cur);
        if (cur.balance == 2) {//左子树高于右子树
            if (height(cur.left.left) >= height(cur.left.right)) {//ll型
                cur = rotate_LL(cur);
            } else {//lr型
                cur = rotate_LR(cur);
            }
        } else if (cur.balance == -2) {//右子树高于左子树
            if (height(cur.right.right) >= height(cur.right.left)) {//rr型
                cur = rotate_RR(cur);
            } else {//rl型
                cur = rotate_RL(cur);
            }
        }
        if (cur.parent != null) {
            return balanced_tree(cur.parent);//找到父节点维护
        } else {
            return cur;
        }
    }

    public static node rotate_LR(node a) {//LR型旋转
        a.left = rotate_RR(a.left);
        return rotate_LL(a);

    }

    public static node rotate_LL(node a) {//LL型旋转
        node cur2 = a.left;
        cur2.parent = a.parent;
        a.left = cur2.right;
        cur2.right = a;
        if (a.left != null)
            a.left.parent = a;
        a.parent = cur2;
        if (cur2.parent != null) {
            if (cur2.parent.right == a) {
                cur2.parent.right = cur2;
            } else {
                cur2.parent.left = cur2;
            }
        }
        update(a);
        update(cur2);
        return cur2;
    }

    public static node rotate_RR(node a) {//RR型旋转
        node cur2 = a.right;
        cur2.parent = a.parent;
        a.right = cur2.left;
        cur2.left = a;
        if (a.right != null)
            a.right.parent = a;
        a.parent = cur2;
        if (cur2.parent != null) {
            if (cur2.parent.right == a) {
                cur2.parent.right = cur2;
            } else {
                cur2.parent.left = cur2;
            }
        }
        update(a);
        update(cur2);
        return cur2;

    }

    public static node rotate_RL(node a) {//RL型旋转
        a.right = rotate_LL(a.right);
        return rotate_RR(a);
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }

        //         public boolean hasNext() {
//             try {
//                 return reader.ready();
//             } catch(IOException e) {
//                 throw new RuntimeException(e);
//             }
//         }
        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch (IOException e) {
                return false;
            }
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecinal() {
            return new BigDecimal(next());
        }
    }

    static class node {

        int balance;
        int height;

        long C;
        long xiajie;
        long shangjie;
        long offset;
        long child;//子树的信息

        long lb;
        long ub;
        long key;//单点信息

        node left;
        node right;
        node parent;

        node(long key, long C, node parent) {
//            long key;
//            if (a>0){
//                key=Math.min(a,C);
//            }else {
//                key=Math.max(a,C*-1);
//            }//先维护输入的offset
            this.key = key;
            this.parent = parent;
            if (key >= 0) {//如果key>0判断下界
                this.lb = Math.min(key, C);
                this.ub = C;
            } else {//如果key<0判断上界
                this.ub = Math.max(C + key, 0);
                this.lb = 0;
            }
            this.C = C;
            this.shangjie = ub;
            this.xiajie = lb;
            this.offset = key;
        }


    }
}//梦里都是平衡树
