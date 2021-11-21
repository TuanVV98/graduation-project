import axios from 'axios';

let token=JSON.parse(localStorage.getItem('token'));
let header={};
if(token){
    header= {'Authorization': 'Bearer '+JSON.parse(localStorage.getItem('token'))};
}

axios.defaults.baseURL='http://localhost:8080/api/v1/'
axios.defaults.headers.common = header;
// console.log(header);

export default axios;
