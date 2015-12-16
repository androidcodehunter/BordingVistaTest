package sharif.bordingvistatestapp.database.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by androidcodehunter on 12/14/2015.
 * Email: sharif.iit.du@gmail.com
 */

public class Department {

    public static final String DEPT_NO = "DeptNo";
    public static final String DEPT_TEXT = "DeptText";
    public static final String DEPT_COUNT = "Count";


    private int mDepartmentNo;
    private String mDepartmentText;
    private int mDepartmentCount;


    public void setDepartmentNo(int departmentNo) {
        this.mDepartmentNo = departmentNo;
    }

    public void setDepartmentCount(int departmentCount) {
        this.mDepartmentCount = departmentCount;
    }


    public int getDepartmentNo() {
        return mDepartmentNo;
    }

    public int getDeptCount() {
        return mDepartmentCount;
    }

    public String getDepartmentText() {
        return mDepartmentText;
    }


    public void setDepartmentText(String departmentText) {
        this.mDepartmentText = departmentText;
    }


    /**
     * Get all department data's from json response.
     *
     * @param result The server response which is used to parse.
     * */
    public static List<Department> getDepartmentsInfoFromJson(JSONObject result) throws JSONException {

        final String TAG_ARTICLE_INQ = "ArticleInq";
        final String TAG_DEPARTMENTS = "Departments";
        final String TAG_DEPT_ARRAY = "Dept";

        List<Department> departments = new ArrayList<>();

        final JSONObject jsonObjectDepartments = result.getJSONObject(TAG_ARTICLE_INQ).getJSONObject(TAG_DEPARTMENTS);

        final JSONArray jsonArrayDept = jsonObjectDepartments.getJSONArray(TAG_DEPT_ARRAY);

        for (int i = 0; i < jsonArrayDept.length(); i++){
            JSONObject jsonObjectDept = (JSONObject) jsonArrayDept.get(i);
            Department department = new Department();
            department.setDepartmentNo(jsonObjectDept.getInt(DEPT_NO));
            department.setDepartmentText(jsonObjectDept.getString(DEPT_TEXT));
            department.setDepartmentCount(jsonObjectDept.getInt(DEPT_COUNT));
            departments.add(department);
        }

        return departments;
    }
}
