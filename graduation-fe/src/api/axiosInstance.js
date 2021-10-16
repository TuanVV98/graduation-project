import axios from 'axios'

const axiosInstance = axios.create({
    baseURL: 'https://616689ac13aa1d00170a6557.mockapi.io/api/aladin',
    headers: {
        'Content-Type': 'Application/json'
    }
})

axiosInstance.interceptors.request.use(function (config) {
    const accessToken = localStorage.getItem('access_token');
    if (accessToken) {
        config.headers = {
            Authorization: 'Bearer ' + accessToken,
        };
    }
    return config;
}, function (error) {
    return Promise.reject(error);
});

axiosInstance.interceptors.response.use(function (response) {

    return response.data;
}, function (error) {

    return Promise.reject(error);
});

export default axiosInstance;

