package sharif.bordingvistatestapp.database.table;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 12/14/2015.
 */
public class Department {

    public static final String DEPT_NO = "DeptNo";
    public static final String DEPT_TEXT = "DeptText";
    public static final String DEPT_COUNT = "Count";


    private String mDepartmentNo;
    private String mDepartmentText;
    private String mDepartmentCount;


    public String getDepartmentNo() {
        return mDepartmentNo;
    }

    public String getDepartmentText() {
        return mDepartmentText;
    }

    public String getDepartmentCount() {
        return mDepartmentCount;
    }

    public void setDepartmentNo(String departmentNo) {
        this.mDepartmentNo = departmentNo;
    }

    public void setDepartmentText(String departmentText) {
        this.mDepartmentText = departmentText;
    }

    public void setDepartmentCount(String departmentCount) {
        this.mDepartmentCount = departmentCount;
    }


    /**
     * Get all department datas from json response.
     *
     * @param result The server response which is used to parse.
     * */
    public static List<Department> getDepartmentsInfoFromJson(JSONArray result) {
        List<Department> departments = new ArrayList<>();

        for (int i = 0; i < result.length(); i++) {
            try {

                JSONObject jsonObject = (JSONObject) result.get(i);
                Department department = new Department();
                department.setDepartmentNo((String) jsonObject.get(DEPT_NO));
                department.setDepartmentText((String) jsonObject.get(DEPT_TEXT));
                department.setDepartmentCount((String) jsonObject.get(DEPT_COUNT));
                departments.add(department);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return departments;
    }
}
