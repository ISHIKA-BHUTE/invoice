import axios from 'axios'

const CUSTOMER_BASE_REST_API_URL = 'http://localhost:8080/customer';
const CUSTOMER_BASE_REST_API_URLL = 'http://localhost:8080/customer/save';
class CustomerService{

    getAllCustomers(){
        return axios.get(CUSTOMER_BASE_REST_API_URL)
    }

    createCustomer(customer){
        return axios.post(CUSTOMER_BASE_REST_API_URLL,customer)
    }

    getCustomerById(customerId){
        return axios.get(CUSTOMER_BASE_REST_API_URL + '/' + customerId);
    }

    updateCustomer(customerId , customer){
        return axios.put(CUSTOMER_BASE_REST_API_URL + '/update/' + customerId , customer)
    }

    deleteCustomer(customerId){
        return axios.delete(CUSTOMER_BASE_REST_API_URL + '/delete/' + customerId);
    }
}

export default new CustomerService();