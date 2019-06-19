package novel.web.entitys;

/**
 * @Classname JSONResponse
 * @Description TODO
 * @Author XinChen
 * @Date 2019/6/19 1:58
 * @Version 1.0
 **/
public class JSONResponse {
    private int status;
    private String desc;
    private Object data;

    public static JSONResponse success(Object data){
        JSONResponse response = new JSONResponse();
        response.setStatus(1);
        response.setData(data);
        return response;
    }
    public static JSONResponse error(String desc){
        JSONResponse response = new JSONResponse();
        response.setStatus(0);
        response.setDesc(desc);
        return response;
    }

    @Override
    public String toString() {
        return "JSONResponse{" +
                "status=" + status +
                ", desc='" + desc + '\'' +
                ", data=" + data +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
