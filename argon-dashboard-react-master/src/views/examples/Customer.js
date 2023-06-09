// import React, { useState, useEffect } from "react";
// import CustomerService from "services/CustomerService";
// import {
//   Table,
//   Card,
//   CardHeader,
//   Button,
//   Modal,
//   Form,
//   FormGroup,
//   Label,
//   Input,
//   ModalHeader,
//   ModalBody,
//   ModalFooter,
// } from "reactstrap";
// import { Link , useNavigate} from "react-router-dom";

// const Customer = () => {
//   const [customers, setCustomers] = useState([]);
//   // const navigate=useNavigate();

//   // const handleSubmit=(e)=>{
//   //   e.preventDefault();
//   //   navigate("/add-Customer");
//   // }


//   useEffect(() => {
//     // Fetch customers from the backend and set the state
//      CustomerService.getAllCustomers().then((response) => {
//       setCustomers(response.data)
//       console.log(response.data);
//      }).catch(error =>{
//        console.log(error);
//      })
//   }, []);
//   return (
//     <>
//       <div className="col" style={{ padding: "10% 5%" }}>
    
//       {/* <Link to="/add-Customer" className="btn btn-primary mb-2" > Add Customer</Link> */}

//         <Card className="bg-default shadow">
//           <CardHeader className="bg-transparent border-0">
//             <h3 className="text-white mb-0">Customer Table</h3>
//           </CardHeader>
//           <Table className="align-items-center table-dark table-flush">
//             <thead className="thead-dark">
//               <tr>
//                 <th>Customer Id</th>
//                 <th>Customer Name</th>
//                 <th>Address</th>
//                 <th>Customer Email</th>
//                 <th>Phone Number</th>
//                 <th>City</th>
//                 <th>Country</th>

//                 {/* <th>Actions</th> */}
//               </tr>
//             </thead>
//             <tbody>
//               {customers.map((customer) => (
//                 <tr key={customer.customerId}>
//                   <td>{customer.customerId}</td>
//                   <td>{customer.customerName}</td>
//                   <td>{customer.address}</td>
//                   <td>{customer.customerEmail}</td>
//                   <td>{customer.customerPhoneNumber}</td>
//                   <td>{customer.city}</td>
//                   <td>{customer.country}</td>

//                   {/* <td>
//                     <Button
//                       color="secondary"
//                       size="sm"
//                       // onClick={() => editCustomer(customer)}
//                     >
//                       Edit
//                     </Button>{" "}
//                     <Button
//                       color="dark"
//                       size="sm"
//                       // onClick={() => deleteCustomer(customer)}
//                     >
//                       Delete
//                     </Button>
//                   </td> */}
//                 </tr>
//               ))}
//             </tbody>
//           </Table>
//         </Card>
//       </div>
//     </>
//   );
// };

// export default Customer;

// code 2 

import React, { useState, useEffect } from "react";
import { useNavigate , useParams} from "react-router-dom";
import CustomerService from "services/CustomerService";
import {
  Table,
  Card,
  CardHeader,
  Button,
  Modal,
  ModalHeader,
  ModalBody,
  ModalFooter,
  Form,
  FormGroup,
  Label,
  Input,
} from "reactstrap";

function Customer() {
  const navigate = useNavigate();
  const [customers, setCustomers] = useState([]);
  const [customerName, setCustomerName] = useState("");
  const [address, setAddress] = useState("");
  const [customerEmail, setCustomerEmail] = useState("");
  const [customerPhoneNumber, setCustomerPhoneNumber] = useState("");
  const [city, setCity] = useState("");
  const [country, setCountry] = useState("");
  const [modalOpen, setModalOpen] = useState(false);
  const {customerId} = useParams();

  useEffect(() => {
    fetchCustomers();
  }, []);

  const fetchCustomers = () => {
    CustomerService.getAllCustomers()
      .then((response) => {
        setCustomers(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  // useEffect(() => {
  //   CustomerService.getCustomerById(customerId).then((response) =>{
  //     setCustomerName(response.data.customerName)
  //     setAddress(response.data.address)
  //     setCustomerEmail(response.data.customerEmail)
  //     setCustomerPhoneNumber(response.data.customerPhoneNumber)
  //     setCity(response.data.city)
  //     setCountry(response.data.country)
  //   })
  //   .catch((error) => {
  //     console.log(error);
  //   });
  // }, []);
  
  // useEffect(() => {
  //   if (customerId) {
  //     CustomerService.getCustomerById(customerId)
  //       .then((response) => {
  //         const customerData = response.data;
  //         setCustomerName(customerData.customerName);
  //         setAddress(customerData.address);
  //         setCustomerEmail(customerData.customerEmail);
  //         setCustomerPhoneNumber(customerData.customerPhoneNumber);
  //         setCity(customerData.city);
  //         setCountry(customerData.country);
  //       })
  //       .catch((error) => {
  //         console.log(error);
  //       });
  //   }
  // }, [customerId]);

  const toggleModal = () => {
    setModalOpen(!modalOpen);
  };
  // const toggleModal = (customer) => {
  //   if (customer) {
  //     setCustomerName(customer.customerName);
  //     setAddress(customer.address);
  //     setCustomerEmail(customer.customerEmail);
  //     setCustomerPhoneNumber(customer.customerPhoneNumber);
  //     setCity(customer.city);
  //     setCountry(customer.country);
  //   } else {
  //     setCustomerName("");
  //     setAddress("");
  //     setCustomerEmail("");
  //     setCustomerPhoneNumber("");
  //     setCity("");
  //     setCountry("");
  //   }
  //   setModalOpen(!modalOpen);
  // };
  

  const saveOrUpdateCustomer = (e) => {
    e.preventDefault();

    const customer = {
      customerName,
      address,
      customerEmail,
      customerPhoneNumber,
      city,
      country,
    };

  //   if(customerId){
  //     CustomerService.updateCustomer(customerId , customer).then((response) => {
  //       navigate("/admin/customer");
        
  //     })
  //     .catch((error) => {
  //       console.log(error);
  //     });

  //   }
  //  else{
  //   CustomerService.createCustomer(customer)
  //     .then((response) => {
  //       console.log(response.data);
  //       toggleModal();
  //       fetchCustomers();
  //       navigate("/admin/customer");
  //     })
  //     .catch((error) => {
  //       console.log(error);
  //     });
  //   }
  // if (customerId) {
  //   CustomerService.updateCustomer(customerId, customer)
  //     .then(() => {
  //       toggleModal(); // Close the modal
  //       fetchCustomers(); // Fetch the updated list of customers
  //     })
  //     .catch((error) => {
  //       console.log(error);
  //     });
  // } else {
  //   CustomerService.createCustomer(customer)
  //     .then(() => {
  //       toggleModal(); // Close the modal
  //       fetchCustomers(); // Fetch the updated list of customers
  //     })
  //     .catch((error) => {
  //       console.log(error);
  //     });
  // }

    CustomerService.createCustomer(customer)
      .then((response) => {
        console.log(response.data);
        toggleModal();
        fetchCustomers();
        navigate("/admin/customer");
      })
      .catch((error) => {
        console.log(error);
      });
  };
  

  const handleFileUpload = async (e) => {
    const file = e.target.files[0];
    const data = new FormData();
    data.append('file', file);
  
    try {
      const response = await fetch('http://localhost:8080/customer/upload/excel', {
        method: 'POST',
        body: data,
      });
  
      if (!response.ok) {
        throw new Error('Error:', response.status);
      }
  
      fetchCustomers();
    } catch (error) {
      console.error('Error:', error);
    }
  };


  const title = () => {

    if(customerId){
      return <ModalHeader toggle={toggleModal}>Update Customer</ModalHeader>
    }else{
      return <ModalHeader toggle={toggleModal}>Add Customer</ModalHeader>
    }
  }

  return (
    <>
      <div className="col" style={{ padding: "10% 5%" }}>
      <input
        id="fileInput"
        type="file"
        accept=".xlsx, .xls"
        style={{ display: 'none' }}
        onChange={handleFileUpload}
      />
      <Button
        color="primary"
        size="sm"
        onClick={() => document.getElementById('fileInput').click()}
      >
        Upload Customer
      </Button>

        <Button color="primary"  size="sm" onClick={toggleModal} style={{ marginBottom: '7px', marginLeft: 'auto', display: 'block' }}>
          Add Customer
        </Button>

        <Card className="bg-default shadow">
          <CardHeader className="bg-transparent border-0">
            <h3 className="text-white mb-0">Customer Table</h3>
          </CardHeader>
          <Table className="align-items-center table-dark table-flush">
            <thead className="thead-dark">
              <tr>
                <th>Customer Id</th>
                <th>Customer Name</th>
                <th>Address</th>
                <th>Customer Email</th>
                <th>Phone Number</th>
                <th>City</th>
                <th>Country</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {customers.map((customer) => (
                <tr key={customer.customerId}>
                  <td>{customer.customerId}</td>
                  <td>{customer.customerName}</td>
                  <td>{customer.address}</td>
                  <td>{customer.customerEmail}</td>
                  <td>{customer.customerPhoneNumber}</td>
                  <td>{customer.city}</td>
                  <td>{customer.country}</td>
                   <td>
                    <Button
                      color="secondary"
                      size="sm"
                      // onClick={() => editCustomer(customer)}
                      onClick={toggleModal}
                      // onClick={() => toggleModal(customer)}

                    >
                      Edit
                    </Button>{" "}
                    <Button
                      color="dark"
                      size="sm"
                      // onClick={() => deleteCustomer(customer)}
                    >
                      Delete
                    </Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </Card>
      </div>

      <Modal isOpen={modalOpen} toggle={toggleModal}>
        {/* <ModalHeader toggle={toggleModal}>Add Customer</ModalHeader> */}
        {
          title()
        }
        <ModalBody>
          <Form>
            <FormGroup>
              <Label for="customerName">Customer Name</Label>
              <Input
                type="text"
                id="customerName"
                placeholder="Customer Name"
                value={customerName}
                onChange={(e) => setCustomerName(e.target.value)}
              />
            </FormGroup>
            <FormGroup>
              <Label for="address">Address</Label>
              <Input
                type="text"
                id="address"
                placeholder="Address"
                value={address}
                onChange={(e) => setAddress(e.target.value)}
              />
            </FormGroup>
            <FormGroup>
              <Label for="customerEmail">Customer Email</Label>
              <Input
                type="email"
                id="customerEmail"
                placeholder="Customer Email"
                value={customerEmail}
                onChange={(e) => setCustomerEmail(e.target.value)}
              />
            </FormGroup>
            <FormGroup>
              <Label for="customerPhoneNumber">Customer Number</Label>
              <Input
                type="number"
                id="customerPhoneNumber"
                placeholder="Customer Number"
                value={customerPhoneNumber}
                onChange={(e) => setCustomerPhoneNumber(e.target.value)}
              />
            </FormGroup>
            <FormGroup>
              <Label for="city">City</Label>
              <Input
                type="text"
                id="city"
                placeholder="Customer City"
                value={city}
                onChange={(e) => setCity(e.target.value)}
              />
            </FormGroup>
            <FormGroup>
              <Label for="country">Country</Label>
              <Input
                type="text"
                id="country"
                placeholder="Customer Country"
                value={country}
                onChange={(e) => setCountry(e.target.value)}
              />
            </FormGroup>
          </Form>
        </ModalBody>
        <ModalFooter>
          <Button color="primary" onClick={saveOrUpdateCustomer}>
            Save
          </Button>{" "}
          <Button color="secondary" onClick={toggleModal}>
            Cancel
          </Button>
        </ModalFooter>
      </Modal>
    </>
  );
}

export default Customer;
