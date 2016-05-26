package com.example.sony.maytinh;

/**
 * Created by SONY on 4/6/2016.
 */
import java.util.Stack;
public class Cal {

    public static String BieuThuc =  new String(); //Chuỗi biểu thức
    public static String [] InFix = new String[100]; //Mảng chứa biểu thức sau khi tách ra
    public static String [] PosFix = new String[100]; //Mảng chứa biểu thức hậu tố
    public static String S = "-+*/^()"; //Chuỗi chứa các toán tử, đóng ngoặc, mở ngoặc
    public static int N=0; // Số phần tử của mảng Infix
    public static int K=0; //Số phần tử của mảng Posfix
    public static void TaoBieuThuc(String S) //Tạo biểu thức
    {
        BieuThuc = S;
        N=0;
        K=0;
    }
    public static void TachChuoi() //Hàm tách các Token của biểu thức
    {
        String BieuThucChuan = "";
        int j = 0;
        int L = BieuThuc.length(); //Lấy độ dài của chuỗi biểu thức
        for (int i = 0; i < L; i++) {
            if (BieuThuc.charAt(i) != ' ') //Loại bỏ khoảng trắng trong biểu thức
            {
                BieuThucChuan = BieuThucChuan + BieuThuc.charAt(i);
            }
        }
        L = BieuThucChuan.length(); //Lấy độ dài của chuỗi biểu thức đã loại bỏ khoảng trắng
        String Token="";
        for (int i=0; i<L; i++) //Duyệt từng ký tự của chuỗi biểu thức
        {
            if (S.indexOf(BieuThucChuan.charAt(i))>=0) //Nếu là toán tử, dấu (, dấu )
            {
                if (Token.compareTo("")!=0) InFix[N++] = Token.trim();
                InFix[N++] = ""+ BieuThucChuan.charAt(i);
                Token ="";
            }
            else // ko la toan tu
                Token = Token + BieuThucChuan.charAt(i);
        }
        if (S.indexOf(Token)<0)
        {
            InFix[N++] = Token.trim();//N có thể bắt đâu từ 0
        }
    }
    public static int UuTien(String s) //Hàm xét ưu tiên các phép toán
    {
        int ok=0;
        switch (s)
        {
            case "+":
            case "-": ok =  1; break;
            case "*":
            case "/": ok = 2; break;
            case "^": ok = 3; break;
            default : ok = 0; break;
        }
        return ok;
    }
    public static void HauTo() //Chuyển từ InFix sang PosFix
    {
        Stack<String> st = new Stack<String>(); //Khai báo một stack
        String Token;
        boolean stop;
        for (int i=0; i<N; i++)
        {
            switch (InFix[i])
            {
                case "(": st.push(InFix[i]); break; //Nếu là dấu ( thì cho vào stack
                case ")": //Nếu là ) thì lấy tất cả các toán tử trong stack ra cho đến dấu )
                    do
                    {
                        Token = st.pop();
                        if (Token.compareTo("(")!=0)
                            PosFix[K++] = Token;
                    }
                    while (Token.compareTo("(")!=0);
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "^": //Nếu là các toán tử
                    stop = false;
                    while (!st.empty() && !stop)
                    {
                        Token = st.pop();
                        if (UuTien(Token) < UuTien(InFix[i]))//độ ưu tiên toán tử
                        {
                            st.push(Token);
                            stop = true;
                        }
                        else
                            PosFix[K++]= Token; //Lấy toán tử ra PosFix
                    }
                    st.push(InFix[i]); //Cho toán tử vào stack
                    break;
                default: PosFix[K++] = InFix[i]; //Nếu là toán hạng thì cho ra PosFix
            }
        }
        while (!st.empty()) //lấy tất cả các phần tử trong stack ra PosFix
        {
            Token = st.pop();
            PosFix[K++] = Token;
        }
    }
    public static String ChuoiHauTo()
    {
        String S="";
        for (int i=0; i<K; i++)
            S = S + " " + PosFix[i];
        return S;
    }

    public static double Tinh(double a,double b, String op) //Hàm thực hiện các phép toán
    {
        double kq=0;
        switch (op)
        {
            case "+": kq = a+b; break;
            case "-": kq = a-b; break;
            case "*": kq = a*b; break;
            case "/": kq = a/b; break;
            case "^": kq = Math.pow(a,b); break;
        }
        return kq;
    }

    public static double TinhBieuThuc() //Hàm tính Biểu thức PosFix
    {
        double kq = 0, number=0,number1=0, number2=0;
        Stack<String> st = new Stack<String>();
        for (int i=0; i<K; i++)
        {
            switch (PosFix[i])
            {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":      //Nếu gặp toán tử, lần lượt lấy 2 toán hạng trong stack ra
                    number1 = Double.parseDouble(st.pop()); //Toán hạng thứ nhất
                    number2 = Double.parseDouble(st.pop()); //Toán hạng thứ hai
                    number = Tinh(number2,number1,PosFix[i]); //Tính toán
                    st.push("" + number); //Kết quả cho vào lại stack
                    break;
                default:   st.push(PosFix[i]); //Nếu gặp toán hạng cho vào stack
            }
        }
        kq = Double.parseDouble(st.pop()); //Trong stack chỉ còn lại 1 phần tử chính là kết quả
        return kq;
    }
}





