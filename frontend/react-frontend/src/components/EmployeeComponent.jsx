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
                <div className="card column-md-6 offset-md-3">
                    <h3 className='text-center card-header'> View Employee Details </h3>
                    <div className='card-body'>
                        <div className="row">
                            <p><strong>Employee First Name: </strong>{this.state.employeeDto.firstName}</p>
                        </div>
                        <div className="row">
                            <p><strong>Employee Last Name: </strong>{this.state.employeeDto.lastName}</p>
                        </div>
                        <div className="row">
                            <p><strong>Employee Email: </strong>{this.state.employeeDto.email}</p>
                        </div>
                    </div>
                </div>

                <div className="card column-md-6 offset-md-3">
                    <h3 className='text-center card-header'> View Department Details </h3>
                    <div className='card-body'>
                        <div className="row">
                            <p><strong>Department Name: </strong>{this.state.departmentDto.departmentName}</p>
                        </div>
                        <div className="row">
                            <p><strong>Department Description: </strong>{this.state.departmentDto.departmentDescription}</p>
                        </div>
                        <div className="row">
                            <p><strong>Department Description: </strong>{this.state.departmentDto.departmentCode}</p>
                        </div>
                    </div>
                </div>

                <div className="card column-md-6 offset-md-3">
                    <h3 className='text-center card-header'> View Organization Details </h3>
                    <div className='card-body'>
                        <div className="row">
                            <p><strong>Organization Name: </strong>{this.state.organization.organizationName}</p>
                        </div>
                        <div className="row">
                            <p><strong>Organization Description: </strong>{this.state.organization.organizationDescription}</p>
                        </div>
                        <div className="row">
                            <p><strong>Organization Code </strong>{this.state.organization.organizationCode}</p>
                        </div>
                        <div className="row">
                            <p><strong>Organization Created At: </strong>{this.state.organization.createdDate}</p>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default EmployeeComponent;