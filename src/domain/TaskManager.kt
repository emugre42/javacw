package domain

import  javax.swing.table.DefaultTableModel

class TaskManager(private val model: DefaultTableModel) {

    fun addTask(task: Task){
        val row = arrayOf(task.projectName, task.taskName, task.taskNumber)
        model.addRow(row)
    }

    fun deleteTask(i: Int){
        if(i >= 0){
            model.removeRow(i)
        }
    }

    fun updateTask(task: Task, i: Int){
        model.setValueAt(task.projectName, i, 0)
        model.setValueAt(task.taskName, i, 1)
        model.setValueAt(task.taskNumber, i, 2)
    }

    fun clearTask(){
        model.removeRow(0)
        model.removeRow(1)
        model.removeRow(2)
    }

}