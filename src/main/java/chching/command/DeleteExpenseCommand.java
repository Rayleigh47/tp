package chching.command;

import chching.Storage;
import chching.Ui;
import chching.record.ExpenseList;
import chching.record.IncomeList;

public class DeleteExpenseCommand extends Command {
    private final int index;
    public DeleteExpenseCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage) {
        expenses.deleteExpense(index);
        System.out.println("Expense deleted, here is the updated list:");
        expenses.printExpenseList();
    }
    
}
