package fa.training.managements;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import fa.training.entities.Customer;
import fa.training.entities.Employee;
import fa.training.entities.LineItem;
import fa.training.entities.Order;
import fa.training.services.CustomerService;
import fa.training.services.EmployeeService;
import fa.training.services.LineItemService;
import fa.training.services.OrderService;
import fa.training.services.ProductService;
import fa.training.utils.PrintTable;
import fa.training.validations.InputValidation;

/**
 * This program still have some error and bug, database script in folder project, use SQL 2008 r2
 *
 * @author DajtVox
 */
public class SaleManagement {
	final static InputValidation iv = new InputValidation();

	public static void showMenu() {
		System.out.println("============Sale Management System==========");
		System.out.println("SELECT OPTIONS:");
		System.out.println("\t1. Customer");
		System.out.println("\t2. Employee");
		System.out.println("\t3. Order");
		System.out.println("\t4. LineItem");
		System.out.println("\t5. Exit");
	}

	public static void showMenuCustomer() {
		System.out.println("=====CUSTOMER FUNCTION=====");
		System.out.println("\t1. Get all customer");
		System.out.println("\t2. Add new customer");
		System.out.println("\t3. Delete customer");
		System.out.println("\t4. Update customer");
		System.out.println("\t5. Return");
	}

	public static void showMenuEmployee() {
		System.out.println("=====EMPLOYEE FUNCTION=====");
		System.out.println("\t1. Get all employee");
		System.out.println("\t2. Find employee");
		System.out.println("\t3. Add new employee");
		System.out.println("\t4. Return");
	}

	public static void showMenuOrder() {
		System.out.println("=====ORDER FUNCTION=====");
		System.out.println("\t1. Get all order by a specific customer");
		System.out.println("\t2. Show total price of all orders");
		System.out.println("\t3. Find order");
		System.out.println("\t4. Update total price of a order");
		System.out.println("\t5. Add order");
		System.out.println("\t6. Return");
	}

	public static void showMenuLineItem() {
		System.out.println("=====LINEITEM FUNCTION=====");
		System.out.println("\t1. Get all item by a specific order");
		System.out.println("\t2. Add new lineitem for a order");
		System.out.println("\t3. Return");
	}

	public static int selectChoice() {
		System.out.print("Choose your option: ");
		return iv.inputInt();
	}

	public static char selectYesNo() {
		System.out.print("Do you want to continue: ");
		return iv.inputYesNo();
	}

	public static void notiPauseScreen() {
		System.out.println("Enter to continue!!");
		iv.pauseScreen();
	}

	public static void subProgramCustomer() {
		CustomerService cs = new CustomerService();
		while (true) {
			boolean flag = false;
			showMenuCustomer();
			int choice = selectChoice();
			if (choice == 5) {
				break;
			}
			switch (choice) {
			case 1:
				PrintTable.printCustomerTable(cs.getAllCustomer());
				notiPauseScreen();
				iv.pauseScreen();
				break;
			case 2:
				System.out.print("Enter employee name: ");
				String employeeName = iv.inputString();
				flag = false;
				while (!(flag = cs.addCustomer(employeeName))) {
					System.out.println("Add failed!!");
					char choiceYesNo = selectYesNo();
					if (choiceYesNo == 'n') {
						break;
					}
					System.out.print("Enter employee name: ");
					employeeName = iv.inputString();
				}
				if (flag) {
					System.out.println("Add successfully");
				}
				notiPauseScreen();
				break;
			case 3:
				System.out.print("Enter id customer you want to delete: ");
				int customerIdDelete = iv.inputInt();
				flag = false;
				while (flag = (cs.getCustomerById(customerIdDelete) == null)) {
					System.out.println("Can not find customer!!");
					char choiceYesNo = selectYesNo();
					if (choiceYesNo == 'n') {
						break;
					}
					System.out.print("Enter id customer you want to delete: ");
					customerIdDelete = iv.inputInt();
				}
				if (!flag) {
					System.out.println(cs.deleteCustomer(customerIdDelete) ? "Delete failed !" : "Delete successfully");
				}
				notiPauseScreen();
				break;
			case 4:
				System.out.print("Enter id customer you want to update: ");
				int customerIdUpdate = iv.inputInt();
				flag = false;
				while (flag = (cs.getCustomerById(customerIdUpdate) == null)) {
					System.out.println("Can not find customer!!");
					char choiceYesNo = selectYesNo();
					if (choiceYesNo == 'n') {
						break;
					}
					System.out.print("Enter id customer you want to update: ");
					customerIdUpdate = iv.inputInt();
				}
				if (!flag) {
					System.out.print("Enter new name for customer: ");
					String newCustomerName = iv.inputString();
					Customer updateCustomer = new Customer(customerIdUpdate, newCustomerName);
					System.out.println(cs.updateCustomer(updateCustomer) ? "Update successfully" : "Update failed");
				}
				notiPauseScreen();
				break;
			default:
				break;
			}
		}
	}

	public static void subProgramEmployee() {
		EmployeeService es = new EmployeeService();
		while (true) {
			showMenuEmployee();
			int choice = selectChoice();
			if (choice == 4) {
				break;
			}
			switch (choice) {
			case 1:
				PrintTable.printEmployeeTable(es.getAllEmployee());
				notiPauseScreen();
				break;
			case 2:
				System.out.print("Enter employee id to find: ");
				int employeId = iv.inputInt();
				Employee employee = null;
				while ((employee = es.getEmployeeById(employeId)) == null) {
					System.out.println("Can not find employee!!");
					char choiceYesNo = selectYesNo();
					if (choiceYesNo == 'n') {
						break;
					}
					System.out.print("Enter employee id to find: ");
					employeId = iv.inputInt();
				}
				if (employee != null) {
					PrintTable.printEmployeeTable(Arrays.asList(employee));
				}
				notiPauseScreen();
				break;
			case 3:
				System.out.print("Enter name for new employee: ");
				String employeeName = iv.inputString();
				System.out.print("Enter salary for new employee: ");
				double employeeSalary = iv.inputDouble();
				System.out.print("Enter supervisor id for new employee: ");
				int employeeSpvrId = iv.inputInt();
				while (es.getEmployeeById(employeeSpvrId) == null) {
					System.out.println("Can not find employee!!");
					char choiceYesNo = selectYesNo();
					if (choiceYesNo == 'n') {
						employeeSpvrId = -1;
						break;
					}
					System.out.print("Enter supervisor id for new employee: ");
					employeeSpvrId = iv.inputInt();
				}
				if (employeeSpvrId != -1) {
					Employee newEmployee = new Employee(employeeName, employeeSalary, employeeSpvrId);
					System.out.println(es.insertEmployee(newEmployee) ? "Add successfully" : "Add failed");
				}
				notiPauseScreen();
				break;
			default:
				break;
			}
		}
	}

	public static void subProgramOrder() {
		OrderService os = new OrderService();
		while (true) {
			showMenuOrder();
			int choice = selectChoice();
			if (choice == 6) {
				break;
			}
			switch (choice) {
			case 1:
				System.out.print("Enter customer id to find: ");
				int customerId = iv.inputInt();
				List<Order> orders = null;
				while ((orders = os.getAllOrderByCustomerId(customerId)) == null) {
					System.out.println("Can not find any order belong to input customer!!");
					char choiceYesNo = selectYesNo();
					if (choiceYesNo == 'n') {
						break;
					}
					System.out.print("Enter customer id to find: ");
					customerId = iv.inputInt();
				}
				if (orders != null) {
					PrintTable.printOrderTable(orders);
				}
				notiPauseScreen();
				break;
			case 2:
				System.out.println("Total price of all order is " + os.getTotalOfAllOrder());
				notiPauseScreen();
				break;
			case 3:
				System.out.print("Enter order id to find: ");
				int orderId = iv.inputInt();
				Order order = null;
				while ((order = os.getOrderById(orderId)) == null) {
					System.out.println("Can not find order!!");
					char choiceYesNo = selectYesNo();
					if (choiceYesNo == 'n') {
						break;
					}
					System.out.print("Enter order id to find: ");
					orderId = iv.inputInt();
				}
				if (order != null) {
					PrintTable.printOrderTable(Arrays.asList(order));
				}
				notiPauseScreen();
				break;
			case 4:
				System.out.print("Enter order id you want to update total: ");
				int orderIdUpatePrice = iv.inputInt();
				Order orderUpdatePrice = null;
				while ((orderUpdatePrice = os.getOrderById(orderIdUpatePrice)) == null) {
					System.out.println("Can not find order!!");
					char choiceYesNo = selectYesNo();
					if (choiceYesNo == 'n') {
						break;
					}
					System.out.print("Enter order id you want to update total: ");
					orderIdUpatePrice = iv.inputInt();
				}
				if (orderUpdatePrice != null) {
					System.out
							.println(os.updateTotalOrder(orderIdUpatePrice) ? "Update failed" : "Update successfully");
				}
				notiPauseScreen();
				break;
			case 5:
				boolean flag = false;
				int step = 0;
				System.out.print("Enter order date(MM/dd/yyyy): ");
				Date newOrderOrderDate = iv.inputDate();
				step++;
				System.out.print("Enter customer id: ");
				int newOrderCustomerId = iv.inputInt();
				while (flag = (new CustomerService().getCustomerById(newOrderCustomerId) == null)) {
					System.out.println("Can not find customer!!");
					char choiceYesNo = selectYesNo();
					if (choiceYesNo == 'n') {
						break;
					}
					System.out.print("Enter customer id: ");
					newOrderCustomerId = iv.inputInt();
				}
				if (!flag) {
					step++;
				}
				System.out.print("Enter employee id:");
				int newOrderEmployeeId = iv.inputInt();
				while (flag = (new EmployeeService().getEmployeeById(newOrderEmployeeId) == null)) {
					System.out.println("Can not find employee!!");
					char choiceYesNo = selectYesNo();
					if (choiceYesNo == 'n') {
						break;
					}
					System.out.print("Enter employee id:");
					newOrderEmployeeId = iv.inputInt();
				}
				if (!flag) {
					step++;
				}
				if (step == 3) {
					Order newOrder = new Order(newOrderCustomerId, newOrderEmployeeId, newOrderOrderDate);
					System.out.println(os.addOrder(newOrder) ? "Add successfully" : "Add failed");
				}
				notiPauseScreen();
				break;
			default:
				break;
			}
		}
	}

	public static void subProgramLineItem() {
		LineItemService lIs = new LineItemService();
		while (true) {
			showMenuLineItem();
			int choice = selectChoice();
			if (choice == 3) {
				break;
			}
			switch (choice) {
			case 1:
				System.out.print("Enter order id to find items: ");
				int orderId = iv.inputInt();
				List<LineItem> lineItems = null;
				while ((lineItems = lIs.getAllLineItemById(orderId)) == null) {
					System.out.println("Can not find any items in this order id!!");
					char choiceYesNo = selectYesNo();
					if (choiceYesNo == 'n') {
						break;
					}
					System.out.print("Enter order id to find items: ");
					orderId = iv.inputInt();
				}
				if (lineItems != null) {
					PrintTable.printLineItemTable(lineItems);
				}
				notiPauseScreen();
				break;
			case 2:
				int step = 0;
				boolean flag = false;
				System.out.print("Enter Order id: ");
				int newItemOrderId = iv.inputInt();
				while (flag = (new OrderService().getOrderById(newItemOrderId) == null)) {
					System.out.println("Can not find order id!!");
					char choiceYesNo = selectYesNo();
					if (choiceYesNo == 'n') {
						break;
					}
					System.out.print("Enter Order id: ");
					newItemOrderId = iv.inputInt();
				}
				if (!flag) {
					step++;
				}
				System.out.print("Enter product id: ");
				int newItemProductId = iv.inputInt();
				while (flag = (new ProductService().getProductById(newItemProductId) == null)) {
					System.out.println("Can not find product id!!");
					char choiceYesNo = selectYesNo();
					if (choiceYesNo == 'n') {
						break;
					}
					System.out.print("Enter product id: ");
					newItemProductId = iv.inputInt();
				}
				if (!flag) {
					step++;
				}
				System.out.print("Enter quantity: ");
				int newItemQuantity = iv.inputInt();
				step++;
				if (step == 3) {
					LineItem newItem = new LineItem(newItemOrderId, newItemProductId, newItemQuantity);
					System.out.println(lIs.addLineItem(newItem) ? "Add successfully"
							: "Add failed, duplicate pair order id and product id");
				}
				notiPauseScreen();
				break;
			default:
				break;
			}
		}
	}

	public static void program() {
		while (true) {
			showMenu();
			int choice = selectChoice();
			if (choice == 5) {
				break;
			}
			switch (choice) {
			case 1:
				subProgramCustomer();
				break;
			case 2:
				subProgramEmployee();
				break;
			case 3:
				subProgramOrder();
				break;
			case 4:
				subProgramLineItem();
				break;
			default:
				System.out.println("Don't have this option!");
				break;
			}
		}
	}

	public static void main(String[] args) {
		program();
	}

}
