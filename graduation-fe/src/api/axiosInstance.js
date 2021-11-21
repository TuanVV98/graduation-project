import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080/api/v1',
  headers: {
    'Content-Type': 'Application/json',
    'Access-Control-Allow-Origin': '*',
  },
});

axiosInstance.interceptors.request.use(
  function (config) {
    const accessToken = localStorage.getItem('access_token');
    if (accessToken) {
      config.headers = {
        Authorization: 'Bearer ' + accessToken,
      };
    }
    console.log(accessToken);
    return config;
  },
  function (error) {
    return Promise.reject(error);
  }
);

axiosInstance.interceptors.response.use(
  function (response) {
    return response.data;
  },
  function (error) {
    return Promise.reject(error);
  }
);

export default axiosInstance;
