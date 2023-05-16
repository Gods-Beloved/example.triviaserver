package com.example.data.repository

//import com.example.domain.model.UserSession
import com.example.domain.model.user.account_info.Account
import com.example.domain.repository.TransactionsDataSource
import com.example.domain.repository.UserDataSource
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.or

class TransactionDataSourceImpl(
    database:CoroutineDatabase
):TransactionsDataSource {

    private val transactions = database.getCollection<Account>()

    override suspend fun saveTransaction(account: Account,userId:String,userDataSource:UserDataSource,amount:String): Boolean {

        val existingTransaction = transactions.findOne(filter = Account::reference eq account.reference)


        return if (existingTransaction == null){
            val user = userDataSource.getUserInfo(userId = userId)

            when (account.transferType) {
                "deposit" -> {
                    val prevBalance = user!!.balance
                    val tempBalance = amount.toDouble()

                    val balance = prevBalance + tempBalance


                    userDataSource.updateBalance(
                        userId = userId,
                        amount = balance


                    )
                    transactions.insertOne(document = account).wasAcknowledged()

                }
                "withdraw" -> {
                    val prevBalance = user!!.balance
                    val tempBalance = amount.toDouble()

                    val balance = prevBalance - tempBalance


                    userDataSource.updateBalance(
                        userId = userId,
                        amount = balance


                    )
                    transactions.insertOne(document = account).wasAcknowledged()

                }
                else -> {
                    val prevWinnings = user!!.winnings
                    val tempWinnings = amount.toDouble()

                    val winnings = prevWinnings - tempWinnings


                    userDataSource.updateWinnings(
                        userId = userId,
                        amount = winnings


                    )
                    transactions.insertOne(document = account).wasAcknowledged()

                }
            }



        }else{
            return true
        }

    }




    override suspend fun getOnlyWithdrawal(id: String): List<Account>? {
        return transactions.find(and(Account::id eq id,or(Account::transferType eq "withdrawal",Account::transferType eq "withdraw"))).toList()
    }

    override suspend fun getOnlyDeposit(id: String): List<Account>? {
        return transactions.find(and(Account::id eq id,Account::transferType eq "deposit")).toList()

    }

    override suspend fun getAllAppTransactions(id: String): List<Account>? {

       // return transactions.find(and(Account::email eq id,or(Account::email eq "deposit",Account::transferType eq "withdrawal"))).toList()
        return transactions.find(Account::id eq id).toList()

    }
}