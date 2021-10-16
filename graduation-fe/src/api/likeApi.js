import axiosInstance from "./axiosInstance"

export const BASE_URL = '/likes'

const likeApi = {

    getAll(){
        return axiosInstance.get(BASE_URL)
    },

    like(data){
        return axiosInstance.get(BASE_URL, data)
    },

    dislike(id){
        const url = `${BASE_URL}/${id}`
        return axiosInstance.delete(url)
    }
    
}

export default likeApi;