import axiosInstance from "./axiosInstance"

const accountApi = {

    login(data){
        const url = '/user/auth'
        return axiosInstance.post(url, data)
    }
    
}

export default accountApi;