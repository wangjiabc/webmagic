package tool;

public class User {
    private String key;//keyword
    private String name;//用户名
    private String sex;//性别
    private String minzu;//民族
    private String location;//所在地
    private String identity;//身份学历
    private String school;//学校
    private String major;//专业
    private String work_experience;//工作经验
    private String hope_position;//希望求职岗位
    private String hope_palce;//希望工作地点
    private String hope_salary;//希望待遇
    private String work_type;//希望工作类型




    public String getMinzu() {
        return minzu;
    }
    public void setMinzu(String minzu) {
        this.minzu = minzu;
    }
    public String getWork_experience() {
        return work_experience;
    }
    public void setWork_experience(String work_experience) {
        this.work_experience = work_experience;
    }
    public String getHope_position() {
        return hope_position;
    }
    public void setHope_position(String hope_position) {
        this.hope_position = hope_position;
    }
    public String getHope_palce() {
        return hope_palce;
    }
    public void setHope_palce(String hope_palce) {
        this.hope_palce = hope_palce;
    }
    public String getHope_salary() {
        return hope_salary;
    }
    public void setHope_salary(String hope_salary) {
        this.hope_salary = hope_salary;
    }
    public String getWork_type() {
        return work_type;
    }
    public void setWork_type(String work_type) {
        this.work_type = work_type;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIdentity() {
        return identity;
    }
    public void setIdentity(String identity) {
        this.identity = identity;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }


    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }


    @Override
    public String toString() {
        return "User [name=" + name+ ", sex=" + sex + ", minzu=" + minzu + ", location="
                + location+ ", identity=" + identity + ", school=" + school + ", major=" + major + ", work_experience=" + 
                work_experience+ ", hope_position=" +hope_position  + ", hope_palce=" + hope_palce + ", hope_salary=" +hope_salary
                + ", work_type=" +work_type + "]";

    }
}
