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
			+ "id_destination_bank_account, transaction_amount, transaction_date, transaction_type_id, "
			+ "transaction_description)"
			+ " VALUES(?,?,?,?,(select id_transaction_type from project_console_bank.transaction_type"
			+ " WHERE transaction_type = ?))";
	
	public static final String RECORDWITHDRAWTRANSACTION = "INSERT INTO project_console_bank.transactions("
			+ "id_source_bank_account, transaction_amount, transaction_date, transaction_type_id, "
			+ "transaction_description)"
			+ " VALUES(?,?,?,?,(select id_transaction_type from project_console_bank.transaction_type"
			+ " WHERE transaction_type = ?))";
	
	public static final String RECORDTRANSACTION = "INSERT INTO project_console_bank.transactions("
			+ "id_source_bank_account, id_destination_bank_account, transaction_amount,"
			+ " transaction_date, transaction_type_id, transaction_description)"
			+ " VALUES(?,?,?,?,?,(select id_transaction_type from project_console_bank.transaction_type"
			+ " WHERE transaction_type = ?))";
	
	public static final String RECORDTRANSFERTRANSACTION = "INSERT INTO project_console_bank.transfer_transactions("
			+ "id_source_bank_account, id_destination_bank_account, transfer_amount, "
			+ " transfer_date, transfer_description, is_transfer_cleared, transaction_type_id) "
			+ " VALUES(?,?,?,?,?,?,(select id_transaction_type from project_console_bank.transaction_type "
			+ " WHERE transaction_type = ?))";
	
	public static final String GETLASTBANKACCTINSERTED = "SELECT bank_account_id, date_bank_acct_creation"
			+ " FROM project_console_bank.bank_account ORDER BY date_bank_acct_creation DESC LIMIT 1";
	
	public static final String GETLASTTRANSACTIONINSERTED = "SELECT id_transaction, transaction_amount"
			+ " FROM project_console_bank.transactions ORDER BY transaction_date DESC LIMIT 1";
	
	public static final String GETLASTTRANSFERTRANSACTIONINSERTED = "SELECT id_transfer_transaction, transfer_amount"
			+ " FROM project_console_bank.transfer_transactions ORDER BY transfer_date DESC LIMIT 1";
	
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
	
	public static final String UPDATEBANKACCOUNTAPPROVALSTATUS = "UPDATE project_console_bank.bank_account_for_approval as bafa "
			+ " set bank_acct_appr_pending = ?, bank_acct_appr_status = ? "
			+ " from project_console_bank.bank_account as ba "
			+ " where ba.bank_account_id = ? and ba.bank_account_id = bafa.bank_account_appr_id";

	public static final String UPDATETRANSFERTRANSACTIONCLEARANCESTATUS = 
			"UPDATE project_console_bank.transfer_transactions as tt "
			+ " SET is_transfer_cleared = ? "
			+ " where tt.id_transfer_transaction = ?";

	public static final String UPDATETRANSFERTRANSACTIONTYPEANDCLEARANCESTATUS = 
			"UPDATE project_console_bank.transfer_transactions as tt "
			+ " SET is_transfer_cleared = ?, "
			+ " transaction_type_id = (select id_transaction_type from project_console_bank.transaction_type "
			+ "	WHERE transaction_type = ?) "
			+ " where tt.id_transfer_transaction = ?";
	
	//get account by number
	public static final String GETBANKACCOUNTBYNUMBER = "SELECT bank_account_id, bank_account_number,"
			+ " bank_account_owner_id, date_bank_acct_creation, type_account_status, bank_account_balance"
			+ " FROM project_console_bank.bank_account AS ba "
			+ " left join project_console_bank.account_status AS a_s ON ba.status_bank_account_id = a_s.id_account_status"
			+ " WHERE bank_account_number = ?";

	//get account by ID
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
			+ "	transaction_date, transaction_description"
			+ " FROM project_console_bank.transactions AS t"
			+ " LEFT JOIN project_console_bank.transaction_type AS tt ON t.transaction_type_id = tt.id_transaction_type"
			+ " ORDER BY transaction_date ASC";
	
	public static final String GETALLTRANSFERTRANSACTIONSBYRECIPIENTUSER = "SELECT id_transfer_transaction, "
			+ " id_source_bank_account, id_destination_bank_account, transfer_amount, transaction_type, "
			+ " transfer_date, transfer_description, is_transfer_cleared, "
		    + " ba1.bank_account_number as destination_bk_acct_no, " 
		    + " ba1.bank_account_balance as destination_bk_acct_balance, " 
		    + " as1.type_account_status as destination_type_acct_status, " 
		    + " ba1.bank_account_owner_id as destination_bk_acct_owner_id, " 
		    + " u1.first_name as recipient_fname, u1.last_name as recipient_lname, " 
		    + " ba2.bank_account_number as source_bk_acct_no, " 
		    + " ba2.bank_account_balance as source_bk_acct_balance, " 
		    + " as2.type_account_status as source_type_acct_status, " 
		    + " ba2.bank_account_owner_id as source_bk_acct_owner_id, " 
		    + " u2.first_name as sender_fname, u2.last_name as sender_lname " 
			+ " FROM project_console_bank.transfer_transactions AS trft "
			+ " LEFT JOIN project_console_bank.bank_account AS ba1 ON trft.id_destination_bank_account = ba1.bank_account_id "
			+ " left join project_console_bank.bank_account AS ba2 ON trft.id_source_bank_account = ba2.bank_account_id "
			+ " LEFT JOIN project_console_bank.user as u1 on ba1.bank_account_owner_id = u1.user_id "
			+ " left join project_console_bank.user as u2 on ba2.bank_account_owner_id = u2.user_id "
			+ " LEFT JOIN project_console_bank.transaction_type AS tt ON trft.transaction_type_id = tt.id_transaction_type "
			+ " LEFT JOIN project_console_bank.account_status as as1 on ba1.status_bank_account_id = as1.id_account_status "
			+ " left join project_console_bank.account_status as as2 on ba2.status_bank_account_id = as2.id_account_status "
			+ " WHERE u1.user_id = ? AND tt.transaction_type = ? AND trft.is_transfer_cleared = ? ORDER BY transfer_date asc";
	
	public static final String GETTRANSFERTRANSACTIONBYID = "SELECT id_transfer_transaction, "
			+ " id_source_bank_account, id_destination_bank_account, transfer_amount, transaction_type, "
			+ " transfer_date, transfer_description, is_transfer_cleared, "
		    + " ba1.bank_account_number as destination_bk_acct_no, " 
		    + " ba1.bank_account_balance as destination_bk_acct_balance, " 
		    + " as1.type_account_status as destination_type_acct_status, " 
		    + " ba1.bank_account_owner_id as destination_bk_acct_owner_id, " 
		    + " u1.first_name as recipient_fname, u1.last_name as recipient_lname, " 
		    + " ba2.bank_account_number as source_bk_acct_no, " 
		    + " ba2.bank_account_balance as source_bk_acct_balance, " 
		    + " as2.type_account_status as source_type_acct_status, " 
		    + " ba2.bank_account_owner_id as source_bk_acct_owner_id, " 
		    + " u2.first_name as sender_fname, u2.last_name as sender_lname " 
		    + " FROM project_console_bank.transfer_transactions AS trft "
			+ " LEFT JOIN project_console_bank.bank_account AS ba1 ON trft.id_destination_bank_account = ba1.bank_account_id "
			+ " left join project_console_bank.bank_account AS ba2 ON trft.id_source_bank_account = ba2.bank_account_id "
			+ " LEFT JOIN project_console_bank.user as u1 on ba1.bank_account_owner_id = u1.user_id "
			+ " left join project_console_bank.user as u2 on ba2.bank_account_owner_id = u2.user_id "
			+ " LEFT JOIN project_console_bank.transaction_type AS tt ON trft.transaction_type_id = tt.id_transaction_type "
			+ " LEFT JOIN project_console_bank.account_status as as1 on ba1.status_bank_account_id = as1.id_account_status "
			+ " left join project_console_bank.account_status as as2 on ba2.status_bank_account_id = as2.id_account_status "
			+ " WHERE trft.id_transfer_transaction = ? ORDER BY transfer_date asc";
	
	public static final String GETBANKACCTSBYAPPROVALSTATUS = "SELECT bank_account_id, bank_account_number, "
			+ "bank_account_owner_id, type_account_status, bank_acct_appr_status, bank_acct_appr_pending "
			+ "FROM project_console_bank.bank_account as ba "
			+ "LEFT JOIN project_console_bank.account_status a_s ON "
			+ "ba.status_bank_account_id = a_s.id_account_status "
			+ "LEFT JOIN project_console_bank.bank_account_for_approval bafa "
			+ "ON ba.bank_account_id = bafa.bank_account_appr_id "
			+ "WHERE bank_acct_appr_pending = ? ORDER BY bank_account_id ASC";
}