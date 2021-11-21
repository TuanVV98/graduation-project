import axiosInstance from './axiosInstance';

export const BASE_URL = '/dentists';

const dentistApi = {
  getAll(params) {
    const url = '/dentists';
    return axiosInstance.get(url, { params });
  },

  getById(id) {
    const url = `/dentists/${id}`;
    return axiosInstance.get(url);
  },
};

export default dentistApi;
