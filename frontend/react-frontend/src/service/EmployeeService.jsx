import axios from 'axios'

const EMPLOYEE_BASE_URI = "http://localhost:9191/api/employees"

const EMPLOYEE_ID = 2;

class EmployeeService{
    
    getEmployee() {
        return axios.get(EMPLOYEE_BASE_URI + '/' + EMPLOYEE_ID);
    }

}

export default new EmployeeService