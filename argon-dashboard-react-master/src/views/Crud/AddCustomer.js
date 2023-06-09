import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import CustomerService from "services/CustomerService";

function AddCustomer() {
  const navigate = useNavigate();
  const [customerName, setCustomerName] = useState("");
  const [address, setAddress] = useState("");
  const [customerEmail, setCustomerEmail] = useState("");
  const [customerPhoneNumber, setCustomerPhoneNumber] = useState("");
  const [city, setCITY] = useState("");
  const [country, setCountry] = useState("");

  const saveCustomer = (e) => {
    e.preventDefault();

    const customer = {
      customerName,
      address,
      customerEmail,
      customerPhoneNumber,
      city,
      country,
    };
    CustomerService.createCustomer(customer)
      .then((response) => {
        console.log(response.data);
        navigate("/admin/customer");
      })
      .catch((error) => {
        console.log(error);
      });
  };
  return (
    <div>
      <br></br>
      <div className="container">
        <div className="row">
          <div className="card col-md-6 offset-md-3 offset-md-3">
            Add Customer
            <div className="card-body">
              <form>
                <div className="form-group">
                  <label>Customer Name: </label>
                  <input
                    placeholder="Customer Name"
                    name="customerName"
                    className="form-control"
                    value={customerName}
                    onChange={(e) => setCustomerName(e.target.value)}
                  />
                </div>
                <div className="form-group">
                  <label> Address </label>
                  <input
                    placeholder="Address"
                    name="address"
                    className="form-control"
                    value={address}
                    onChange={(e) => setAddress(e.target.value)}
                  />
                </div>
                <button
                  className="btn btn-success"
                  onClick={(e) => saveCustomer(e)}
                >
                  Save
                </button>
                {/* <button
                  className="btn btn-danger"
                  onClick={this.cancel.bind(this)}
                  style={{ marginLeft: "10px" }}
                >
                  Cancel
                </button> */}
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AddCustomer;
