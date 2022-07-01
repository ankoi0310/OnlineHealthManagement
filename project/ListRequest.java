package project;

import java.util.ArrayList;

/**
 * 
 * @author Windows 10 Đây là một lớp tạo 1 đối tượng duy nhất
 */
public class ListRequest {
	private static ListRequest listR;
	private ArrayList<Request> requests = new ArrayList<>();

	private ListRequest() {

	}

	public static ListRequest getInstance() {
		if (listR == null) {
			listR = new ListRequest();
			Request re = new Request("Nam", "male", 22, "dfdsfdsf", "dsfdsf", "dfdsaf");
			listR.addRequest(re);
		}

		return listR;
	}

	//thêm yêu cầu
	public void addRequest(Request request) {
		requests.add(request);
	}

	//tìm yêu cầu theo số dòng đã bấm
	public Request findRe(int row) {
		return requests.get(row);
	}
	
	public Request findReWithName(String name) {
		Request result = null;
		for(Request re: requests) {
			if(re.getName().equals(name)) {
				result = re;
				break;
			}
		}
		return result;
	}

	//thay đôi yêu cầu 
	//cách làm: thêm yêu cầu mới thay đổi vào sau yêu cầu cũ
	//xóa yêu cầu cũ
	public void change(int row, Request request) {
		requests.add(row + 1, request);
		requests.remove(row);
	}

	public void deleteRe(int row) {
		requests.remove(row);
	}
	//get list yêu cầu
	public ArrayList<Request> getRequestList() {
		return requests;
	}

	//in list yêu cầu
	public void print() {
		for (Request re : requests) {
			System.out.println(re);
			System.out.println("\n---------------\n");
		}
	}
}
