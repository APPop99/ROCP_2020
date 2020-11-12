package app.bankingApp.DAO.dbUtil;

public class BankAccountOperationsQueries 
{
	public static final String INSERTBANKACCOUNT = "INSERT INTO project_console_bank.bank_account(bank_account_number, "
			+ "bank_account_balance, bank_account_owner_id, date_bank_account_creation, "
			+ "date_bank_account_approval, date_bank_account_deletion, status_bank_account_id) "
			+ "VALUES(?,?,?,?,?,?,(select id_account_status from project_console_bank.account_status "
			+ "WHERE type_account_status=?))";
//			+ "RETURN bank_account_id";

	public static final String GETLASTBANKACCTINSERTED = "SELECT bank_account_id, date_bank_account_creation"
			+ " FROM project_console_bank.bank_account ORDER BY date_bank_account_creation DESC LIMIT 1";
	
	public static final String INSERTBANKACCTTOAPPROVALTABLE = 
			"INSERT INTO project_console_bank.bank_account_for_approval (bank_account_appr_id, "
			+ "bank_acct_appr_status, bank_acct_appr_pending)"
			+ "VALUES(?,?,?)";
	
	public static final String UPDATEBANKACCOUNTSTATUS = "UPDATE project_console_bank.bank_account as ba"
			+ " set status_bank_account_id = id_account_status "
			+ " from project_console_bank.account_status as a_s "
			+ " where ba.bank_account_id = ? and a_s.type_account_status = ?";
	
	public static final String UPDATEBANKACCOUNTAPPROVALSTATUS = "UPDATE project_console_bank.bank_account_for_approval as bafa"
			+ " set bank_acct_appr_pending = ?, bank_acct_appr_status = ?"
			+ " from project_console_bank.bank_account as ba"
			+ " where ba.bank_account_id = ? and ba.bank_account_id = bafa.bank_account_appr_id";
	
	public static final String GETBANKACCOUNTBYNUMBER = "SELECT bank_account_id, bank_account_number,"
			+ " bank_account_owner_id, date_bank_account_creation, type_account_status"
			+ " FROM project_console_bank.bank_account AS ba "
			+ " left join project_console_bank.account_status AS as ON ba.status_bank_account_id = as.id_account_status"
			+ " WHERE bank_account_number = ?";
}