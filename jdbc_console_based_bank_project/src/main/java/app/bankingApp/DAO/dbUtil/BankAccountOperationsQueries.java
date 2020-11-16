package app.bankingApp.DAO.dbUtil;

public class BankAccountOperationsQueries 
{
	public static final String INSERTBANKACCOUNT = "INSERT INTO project_console_bank.bank_account(bank_account_number, "
			+ "bank_account_balance, bank_account_owner_id, date_bank_acct_creation, "
			+ "date_bank_acct_approval, date_bank_acct_deletion, status_bank_account_id) "
			+ "VALUES(?,?,?,?,?,?,(select id_account_status from project_console_bank.account_status "
			+ "WHERE type_account_status=?))";
//			+ "RETURN bank_account_id";

	public static final String RECORDDEPOSITTRANSACTION = "INSERT INTO project_console_bank.transactions("
			+ "id_destination_bank_account, transaction_amount, transaction_date, transaction_type_id)"
			+ " VALUES(?,?,?,(select id_transaction_type from project_console_bank.transaction_type"
			+ " WHERE transaction_type = ?))";
	
	public static final String RECORDWITHDRAWTRANSACTION = "INSERT INTO project_console_bank.transactions("
			+ "id_source_bank_account, transaction_amount, transaction_date, transaction_type_id)"
			+ " VALUES(?,?,?,(select id_transaction_type from project_console_bank.transaction_type"
			+ " WHERE transaction_type = ?))";
	
	public static final String RECORDTRANSACTION = "INSERT INTO project_console_bank.transactions("
			+ "id_source_bank_account, id_destination_bank_account, transaction_amount,"
			+ " transaction_date, transaction_type_id)"
			+ " VALUES(?,?,?,?,(select id_transaction_type from project_console_bank.transaction_type"
			+ " WHERE transaction_type = ?))";
	
	public static final String GETLASTBANKACCTINSERTED = "SELECT bank_account_id, date_bank_acct_creation"
			+ " FROM project_console_bank.bank_account ORDER BY date_bank_acct_creation DESC LIMIT 1";
	
	public static final String GETLASTTRANSACTIONINSERTED = "SELECT id_transaction, transaction_amount"
			+ " FROM project_console_bank.transactions ORDER BY transaction_date DESC LIMIT 1";
	
	public static final String INSERTBANKACCTTOAPPROVALTABLE = 
			"INSERT INTO project_console_bank.bank_account_for_approval (bank_account_appr_id, "
			+ "bank_acct_appr_status, bank_acct_appr_pending)"
			+ "VALUES(?,?,?)";
	
	public static final String UPDATEBANKACCOUNTSTATUS = "UPDATE project_console_bank.bank_account as ba"
			+ " set date_bank_acct_approval = ?, status_bank_account_id = id_account_status2"
			+ " from project_console_bank.account_status as a_s"
			+ " where ba.bank_account_id = ? and a_s.type_account_status = ?";

	public static final String UPDATEBANKACCOUNTBALANCE = "UPDATE project_console_bank.bank_account as ba"
			+ " set bank_account_balance = ? "
			+ " where ba.bank_account_id = ?";
	
	public static final String UPDATEBANKACCOUNTAPPROVALSTATUS = "UPDATE project_console_bank.bank_account_for_approval as bafa"
			+ " set bank_acct_appr_pending = ?, bank_acct_appr_status = ?"
			+ " from project_console_bank.bank_account as ba"
			+ " where ba.bank_account_id = ? and ba.bank_account_id = bafa.bank_account_appr_id";

	//get account 
	public static final String GETBANKACCOUNTBYNUMBER = "SELECT bank_account_id, bank_account_number,"
			+ " bank_account_owner_id, date_bank_acct_creation, type_account_status"
			+ " FROM project_console_bank.bank_account AS ba "
			+ " left join project_console_bank.account_status AS a_s ON ba.status_bank_account_id = a_s.id_account_status"
			+ " WHERE bank_account_number = ?";

	//get account 	
	public static final String GETBANKACCOUNTBYID = "SELECT bank_account_id, bank_account_number, "
			+ " bank_account_balance, bank_account_owner_id, date_bank_acct_creation, type_account_status "
			+ " FROM project_console_bank.bank_account AS ba "
			+ " LEFT JOIN project_console_bank.account_status AS a_s "
			+ " ON ba.status_bank_account_id = a_s.id_account_status "
			+ " WHERE bank_account_id = ?";		
								
	public static final String GETBANKACCOUNTLISTBYUSER = "SELECT bank_account_id, bank_account_number,"
			+ " date_bank_acct_creation, first_name, last_name, type_account_status, bank_account_balance"
			+ " FROM project_console_bank.bank_account AS ba"
			+ " LEFT JOIN project_console_bank.user AS u ON ba.bank_account_owner_id = u.user_id"
			+ " LEFT JOIN project_console_bank.account_status AS a_s"
			+ " ON ba.status_bank_account_id = a_s.id_account_status"
			+ " WHERE user_id = ? ORDER BY date_bank_acct_creation ASC";
	
	public static final String GETALLTRANSACTIONS = "SELECT id_transaction, id_source_bank_account,"
			+ " id_destination_bank_account, transaction_amount, transaction_type,"
			+ "	transaction_date"
			+ " FROM project_console_bank.transactions AS t"
			+ " LEFT JOIN project_console_bank.transaction_type AS tt ON t.transaction_type_id = tt.id_transaction_type"
			+ " ORDER BY transaction_date ASC";
	
	public static final String GETBANKACCTSBYAPPROVALSTATUS = "SELECT bank_account_id, bank_account_number, "
			+ "bank_account_owner_id, type_account_status, bank_acct_appr_status, bank_acct_appr_pending "
			+ "FROM project_console_bank.bank_account as ba "
			+ "LEFT JOIN project_console_bank.account_status a_s ON "
			+ "ba.status_bank_account_id = a_s.id_account_status "
			+ "LEFT JOIN project_console_bank.bank_account_for_approval bafa "
			+ "ON ba.bank_account_id = bafa.bank_account_appr_id "
			+ "WHERE bank_acct_appr_pending = ? ORDER BY bank_account_id ASC";
}