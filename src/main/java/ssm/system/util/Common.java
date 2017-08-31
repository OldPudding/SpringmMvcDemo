package ssm.system.util;

public class Common {
	//角色常量
	public final static int ROLE_FIRST  = 3;//初审
	public final static int ROLE_USER  = 2;//用户
	public final static int ROLE_RECHECK = 4;//复审
	public final static int ROLE_FINAL  = 5;//终审
	public final static int ROLE_ADMIN  = 1;//管理员
	
	//审读状态
	public final static int STATUS_BACK  = 0;//驳回
	public final static int STATUS_FIRSTTRAIL  = 1;//初审中
	public final static int STATUS_RECHECK = 2;//复审中
	public final static int STATUS_FINALTRAIL  = 3;//终审中
	public final static int STATUS_TRAIL_OK  = 4;//通过审核
	public final static int STATUS_APPLICATION  = 5;//待申请
	
	//流程审批状态
	public final static String FIRSTTRAIL_OK = "初审同意";
	public final static String FIRSTTRAIL_BACK = "初审驳回";
	public final static String RECHECK_OK = "复审同意";
	public final static String RECHECK_BACK = "复审驳回";
	public final static String FINALTRAIL_OK= "终审同意";
	public final static String FINALTRAIL_BACK= "终审驳回";
	public final static String USER_BACK= "用户撤销申请";
	
	//流程名
	public final static String SUBMIT_NAME = "提交到初审";
	public final static String FIRSTTRAIL_NAME = "初审";
	public final static String RECHECK_NAME = "复审";
	public final static String FINALTRAIL_NAME = "终审";
	public final static String USER_BACK_NAME = "撤销";
 
	
	/** 性别 */
	public final static int MAN = 0;
	public final static int WOMAN = 1;
}
