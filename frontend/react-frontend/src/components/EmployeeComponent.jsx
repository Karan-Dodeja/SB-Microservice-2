import React, { Component } from 'react';
import EmployeeService from "../service/EmployeeService";

class EmployeeComponent extends Component {

    constructor(props) {
        super(props);

        this.state = {
            employeeDto: {},
            departmentDto: {},
            organization: {}
        }
    }

    componentDidMount() {
        EmployeeService.getEmployee().then((response) => {
            this.setState({ employeeDto: response.data.employeeDto })
            this.setState({ departmentDto: response.data.departmentDto })
            this.setState({ organization: response.data.organization })

            console.log(this.state.employeeDto)
            console.log(this.state.departmentDto)
            console.log(this.state.organization)
        })
    }

    render() {
        return (
            <div>

            </div>
        )
    }
}

export default EmployeeComponent;