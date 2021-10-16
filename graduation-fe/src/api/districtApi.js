import axiosInstance from "./axiosInstance"

export const BASE_URL = '/districts'

const districtApi = {

    getAll(){
        return axiosInstance.get(BASE_URL)
    },

    getById(id){
        const url = `/vouchers/${id}`
        return axiosInstance.get(url)
    },

    create(data){
        const url = '/vouchers'
        return axiosInstance.get(url, data)
    },

    update(id, data){
        const url = `/vouchers/${id}`
        return axiosInstance.put(url, data)
    },

    delete(id){
        const url = `/vouchers/${id}`
        return axiosInstance.delete(url)
    }
    
}

export default districtApi;