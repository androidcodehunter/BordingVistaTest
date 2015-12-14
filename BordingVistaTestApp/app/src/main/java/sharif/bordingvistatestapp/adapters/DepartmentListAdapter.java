package sharif.bordingvistatestapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sharif.bordingvistatestapp.R;
import sharif.bordingvistatestapp.database.table.Department;

/**
 * Created by androidcodehunter on 12/14/2015.
 * Email: sharif.iit.du@gmail.com
 */

public class DepartmentListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Department> mDepartments;

    public DepartmentListAdapter() {
    }

    /**
     * Set department list and notify the adapter about the changes.
     *
     * @param departments
     */
    public void setDepartments(List<Department> departments) {
        this.mDepartments = departments;
        notifyDataSetChanged();
    }


    /**
     * ViewHolder pattern
     */
    private class DepartmentViewHolder extends RecyclerView.ViewHolder {
        public TextView textDeptNo, textDeptText, textDeptCount;

        public DepartmentViewHolder(View view) {
            super(view);
            textDeptNo = (TextView) view.findViewById(R.id.text_department_no);
            textDeptText = (TextView) view.findViewById(R.id.text_department_text);
            textDeptCount = (TextView) view.findViewById(R.id.text_department_count);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.department_list_item, parent, false);
        return new DepartmentViewHolder(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final Department department = mDepartments.get(position);
        DepartmentViewHolder holder = (DepartmentViewHolder) viewHolder;

        holder.textDeptNo.setText(String.valueOf(department.getDepartmentNo()));
        holder.textDeptText.setText(department.getDepartmentText());
        holder.textDeptCount.setText(String.valueOf(department.getDeptCount()));
        holder.itemView.setTag(position);
    }

    /**
     * {@inheritDoc}
     *
     * @return mDepartments item count
     */
    @Override
    public int getItemCount() {
        return mDepartments != null ? mDepartments.size() : 0;
    }

}

