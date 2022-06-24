package fa.training.utils;

import java.util.ArrayList;
import java.util.List;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.ColumnData;

import fa.training.entities.Customer;
import fa.training.entities.Employee;
import fa.training.entities.LineItem;
import fa.training.entities.Order;
public class PrintTable {
	public static void printCustomerTable(List<Customer> customers) {
		List<ColumnData<Customer>> listData = new ArrayList<ColumnData<Customer>>();
		listData.add(new Column().header("Customer ID").with(Customer -> Integer.toString(Customer.getCustomerId())));
		listData.add(new Column().header("Customer Name").with(Customer::getCustomerName));
		System.out.println(AsciiTable.getTable(customers, listData));
	}
	
	public static void printEmployeeTable(List<Employee> employees) {
		List<ColumnData<Employee>> listData = new ArrayList<ColumnData<Employee>>();
		listData.add(new Column().header("Employee ID").with(Employee -> Integer.toString(Employee.getEmployeeId())));
		listData.add(new Column().header("Employee Name").with(Employee::getEmployeeName));
		listData.add(new Column().header("Salary").with(Employee -> Double.toString(Employee.getSalary())));
		listData.add(new Column().header("Supervisor ID").with(Employee -> Integer.toString(Employee.getSpvrId())));
		System.out.println(AsciiTable.getTable(employees, listData));
	}
	
	public static void printOrderTable(List<Order> orders) {
		List<ColumnData<Order>> listData = new ArrayList<ColumnData<Order>>();
		listData.add(new Column().header("Order ID").with(Order -> Integer.toString(Order.getOrderId())));
		listData.add(new Column().header("Order Date").with(Order -> Order.getOrderDate() == null ? "NULL" : Order.getOrderDate().toString()));
		listData.add(new Column().header("Customer ID").with(Order -> Integer.toString(Order.getCustomerId())));
		listData.add(new Column().header("Employee ID").with(Order -> Integer.toString(Order.getEmployeeId())));
		listData.add(new Column().header("Total").with(Order -> Double.toString(Order.getTotal())));
		System.out.println(AsciiTable.getTable(orders, listData));
	}
	
	public static void printLineItemTable(List<LineItem> lineItems) {
		List<ColumnData<LineItem>> listData = new ArrayList<ColumnData<LineItem>>();
		listData.add(new Column().header("Order ID").with(LineItem -> Integer.toString(LineItem.getOrderId())));
		listData.add(new Column().header("Product ID").with(LineItem -> Integer.toString(LineItem.getProductId())));
		listData.add(new Column().header("Quantity").with(LineItem -> Integer.toString(LineItem.getQuantity())));
		listData.add(new Column().header("Price").with(LineItem -> Double.toString(LineItem.getPrice())));
		System.out.println(AsciiTable.getTable(lineItems, listData));
	}
}
