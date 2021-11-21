import axiosInstance from "./axiosInstance";

const scheduleTimeApi = {
  getById(params) {
    const url = `schedule-time/hour`;
    return axiosInstance.get(url,{ params });
  },
  getByDentist(params) {
    const url = "schedule-time/dentist";
    return axiosInstance.get(url, { params });
  },
};

export default scheduleTimeApi;
