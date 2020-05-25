package qdu.java.recruit.util;

/**
 * @author 陈淯
 * @date 2020/5/22 23:42
 */
public class StateUtil {
    //新、备选、放弃、未通过、通过、一面、二面、三面，其余大于三面的简历
    //0       1     -1    -2      -3     2    3     4         >4

    public static String getState(int state){
        if (state == 0){
            return "新简历";
        }if (state==1){
            return "备选";
        }if (state==-1){
            return "已放弃";
        }if (state==-2){
            return "未通过";
        }if (state==-3){
            return "已通过";
        }if (state==2){
            return "一面";
        }if (state==3){
            return "二面";
        }if (state==4){
            return "三面";
        }if (state>=5){
            return ">3轮";
        }
        return "";
    }
}
