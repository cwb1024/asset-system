import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.List;

public class Test1 {

    public static int reverse(int x){
        int res = 0;
        while (x != 0) {
            int tail = x % 10;
            int newRes = res * 10 + tail;
            if ((newRes - tail) / 10 != res) {
                return 0;
            }
            res = newRes;
            x = x / 10;
        }

        return res;
    }

    public static void test11(){

        String jsonStr = "[{\"name\":\"张三\",\"type\":\"1\",\"email\":\"aa\"}," +
                "{\"name\":\"李四\",\"type\":\"2\",\"email\":\"bb\"}]";

        List<Tdata> tdata = JSONArray.parseArray(jsonStr, Tdata.class);
        System.out.println(tdata);

    }

    public static void main(String[] args) {
        test11();
    }

    @Test
    public void test(){
        String  COMPLEX_JSON_STR = "{\"jsonStr\":[\"111\",\"222\"]}";
        JSONObject jsonObject = JSONObject.parseObject(COMPLEX_JSON_STR);
        String jsonStr = jsonObject.getJSONArray("jsonStr").toJSONString();
        List<String> list = JSONObject.parseArray(jsonStr, String.class);
        System.out.println(list);
    }
}
