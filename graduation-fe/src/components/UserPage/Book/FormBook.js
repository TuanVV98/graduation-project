import { makeStyles } from '@material-ui/core/styles';
import React, { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import moment from 'moment';
import http from '../../service/http-common';
import { Link } from 'react-router-dom';
import scheduleTimeApi from 'api/scheduletimeApi';
import dentistApi from 'api/dentistApi';
import serviceApi from 'api/serviceApi';

export default function FormBook() {
  const useStyles = makeStyles((theme) => ({
    title: {
      fontSize: '2.2em',
      marginBottom: '0.2em',
      color: '#1c1c1c',
      fontWeight: 300,
      fontFamily: 'Roboto',
    },
    span: {
      color: '#00BCD5',
      fontWeight: 'bold',
    },
  }));
  const classes = useStyles();

  const onChangeHandler = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  //submit form
  const onHandleSubmit = (data) => {
    // console.log(data);
    if (formData.scheduleTime.id) {
      onCreateBooking();
    } else if (hourScheduleTime.length !== 0) {
      document.getElementById('scheduleTime').focus();
    }

    if (hourScheduleTime.length === 0) {
      document.getElementById('dayOfWeek').focus();
    }
  };

  const onCreateBooking = () => {
    formData.status = 1;
    formData.deleteAt = false;

    // console.log(formData);
    http({
      url: `http://localhost:8080/api/v1/booking`,
      method: 'POST',
      data: formData,
    })
      .then((response) => {
        const { data } = response;
        createBookingDetail(data.data.id);
      })
      .catch((error) => {
        const { data } = error.response;
        alert('Đặt lịch thất bại');
        data.errors.details !== null && typeof data.errors.details !== 'undefined'
          ? alert(data.errors.details)
          : console.log('ERROR NO MESSAGES');
      });
  };

  // Tạo booking detail
  const createBookingDetail = (id) => {
    bookingDetail.bookingId = id;
    // console.log(bookingDetail)
    // console.log(id);

    http({
      url: `http://localhost:8080/api/v1/booking/detail`,
      method: 'POST',
      data: bookingDetail,
    })
      .then((response) => {
        alert('Đặt lịch thành công, xem chi tiết tại hồ sơ khám');
        sessionStorage.clear();
      })
      .catch((error) => {
        console.log(error, error.response);
        alert('Đặt lịch thất bại');
      });
  };

  /// get user

  //GET DATA NGƯỜI DÙNG TỪ LOCAL STORAGE(ID,ROLE)
  const [auth, setAuth] = useState({});
  useEffect(() => {
    let json = localStorage.getItem('auth');
    if (json !== null && typeof json !== 'undefined') {
      setAuth(JSON.parse(json));
    }
  }, []);

  ///
  //LẤY THÔNG TIN CHI TIẾT NGƯỜI DÙNG KHI CÓ ACCOUNT_ID
  const [customer, setCustomer] = useState({
    id: '',
    fullname: '',
  });
  useEffect(() => {
    if (typeof auth.id !== 'undefined' && auth.id != null) {
      http({
        url: '/customers/accounts/' + auth.id,
        method: 'GET',
      })
        .then((response) => {
          const { data } = response;
          setCustomer(data.data);
          // console.log(data.data);
          formData.customerProfile.id = data.data.id;
        })
        .catch((error) => {
          console.log(error, error.response);
        });
    }
  }, [auth]);
  // console.log(customer);

  const [service, setService] = useState([]);
  const [dentistProfile, setDentistProfile] = useState([]);
  const [params, setParams] = useState({
    dentistProfileId: null,
  });
  const [scheduleTime, setScheduleTime] = useState([]);

  useEffect(() => {
    const fetchService = async () => {
      const response = await serviceApi.getAll();
      setService(response.data);
    };
    fetchService();
  }, []);

  useEffect(() => {
    const fetchDentistProfile = async () => {
      const res = await dentistApi.getAll();
      setDentistProfile(res.data);
    };
    fetchDentistProfile();
  }, []);

  useEffect(() => {
    const fetchScheduleTime = async () => {
      const res = await scheduleTimeApi.getByDentist(params);
      setScheduleTime(res.data);
      // console.log(res.data);
      if (res.data.length === 0) {
        setHourScheduleTime([]);
      }
    };
    fetchScheduleTime();
  }, [params]);

  const [formData, setFormData] = useState({
    id: '',
    service: {
      id: '',
    },
    description: '',
    bookingDate: '',
    customerProfile: {
      id: customer.id,
    },
    dentistProfile: {
      id: '',
    },
    scheduleTime: {
      id: '',
    },
    status: null,
    updateAt: '',
    deleteAt: false,
  });
  const [bookingDetail, setBookingDetail] = useState({
    id: '',
    bookingId: '',
    serviceId: '',
    voucherId: '',
    price: '',
  });

  const onChangeDentist = (e) => {
    formData.scheduleTime.id = '';
    document.getElementById('dayOfWeek').value = '';
    setHourScheduleTime([]);

    if (e.target.value === '') {
      setParams({ dentistProfileId: null });
      setScheduleTime([]);
    } else {
      setParams({
        dentistProfileId: e.target.value,
      });
    }
  };

  const onChangeService = (e) => {
    const myService = service.filter((value) => value.id == e.target.value);
    setBookingDetail({
      ...bookingDetail,
      serviceId: myService[0].id,
      price: myService[0].price,
    });
  };

  const [hourScheduleTime, setHourScheduleTime] = useState([]);
  const [idScheduleTime, setIdScheduleTime] = useState({
    dayOfWeek: null,
    dentistId: null,
  });

  useEffect(() => {
    const fetchDentistTimeOfDay = async () => {
      const res = await scheduleTimeApi.getById(idScheduleTime);
      setHourScheduleTime(res.data);
      console.log(res);
    };
    fetchDentistTimeOfDay();
  }, [idScheduleTime]);

  const onChangeSchedule = (e) => {
    formData.scheduleTime.id = '';

    if (e.target.value === '') {
      setIdScheduleTime({ dayOfWeek: null, dentistId: null });
      // setScheduleTime([]);
      setHourScheduleTime([]);
      // console.log("toang");
    } else {
      // console.log("ui");
      setIdScheduleTime({
        dayOfWeek: e.target.value,
        dentistId: params.dentistProfileId,
      });
    }
  };

  const bookingService = register('service', { required: true });
  const bookingDentist = register('dentist', { required: true });
  const bookingDescription = register('description', { required: true });
  const bookingDayOfWeek = register('dayOfWeek', { required: true });

  const bookingScheduleTime = register('scheduleTime', { required: true });

  const btnAlert = () => {
    alert('Bạn chưa cập nhật thông tin cá nhân');
  };
  return (
    <div
      className="pt-4 mt-5 text-center"
      style={{ backgroundColor: 'rgb(246, 249, 249)', minHeight: '700px' }}
    >
      <h1 className={classes.title}>
        Đặt lịch hẹn với nha sĩ <span className={classes.span}>Smile Dental</span>
      </h1>
      <br />
      <br />
      <div className="container">
        <form onSubmit={handleSubmit(onHandleSubmit)}>
          <div className="row text-start d-flex justify-content-center">
            <div className="col-sm-3 mx-5">
              <div>
                <label htmlFor="customerProfile" className="form-label">
                  Họ và tên:
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="customerProfile"
                  value={customer.fullname}
                  readOnly
                />
              </div>
              <br />

              <div>
                <label htmlFor="serviceId" className="form-label">
                  Dịch vụ:
                </label>
                <select
                  className="form-select"
                  aria-label="Default select example"
                  id="serviceId"
                  name="service"
                  value={formData.service.id}
                  {...bookingService}
                  onChange={(e) => {
                    bookingService.onChange(e);
                    onChangeHandler(e);
                    onChangeService(e);
                    setFormData({
                      ...formData,
                      service: {
                        id: e.target.value,
                      },
                    });
                  }}
                >
                  <option value="">Chọn dịch vụ</option>
                  {service.map((val, idx) => {
                    return (
                      <option key={idx} value={val.id}>
                        {val.name}
                      </option>
                    );
                  })}
                </select>
                {errors.serviceId && <span className="text-danger">Chọn dịch vụ</span>}
              </div>
              <br />

              <div>
                <label htmlFor="dentistProfile" className="form-label">
                  Bác sĩ:
                </label>
                <select
                  className="form-select"
                  aria-label="Default select example"
                  id="dentistProfile"
                  name="dentist"
                  value={formData.dentistProfile.id}
                  {...bookingDentist}
                  onChange={(e) => {
                    bookingDentist.onChange(e);
                    onChangeHandler(e);
                    setFormData({
                      ...formData,
                      dentistProfile: {
                        id: e.target.value,
                      },
                    });
                    onChangeDentist(e);
                  }}
                >
                  <option value="">Chọn bác sĩ</option>
                  {dentistProfile.map((val, idx) => {
                    return (
                      <option key={idx} value={val.id}>
                        {val.fullName}
                      </option>
                    );
                  })}
                </select>
                {errors.dentistProfile && <span className="text-danger">Chọn bác sĩ</span>}
              </div>
              <br />

              <div>
                <label htmlFor="description" className="form-label">
                  Ghi chú(mô tả tình trạng của bạn):
                </label>
                <textarea
                  className="form-control"
                  id="description"
                  rows="3"
                  name="description"
                  value={formData.description}
                  {...bookingDescription}
                  onChange={(e) => {
                    bookingDescription.onChange(e);
                    onChangeHandler(e);
                  }}
                ></textarea>
                {errors.description && (
                  <span className="text-danger">{errors.description.message}</span>
                )}
              </div>
              <br />
            </div>

            <div className="col-sm-3 mx-5">
              <div>
                <label htmlFor="dayOfWeek" className="form-label mt-1">
                  Chọn ngày:
                </label>
                <br />
                <select
                  className="form-select"
                  aria-label="Default select example"
                  id="dayOfWeek"
                  name="dayOfWeek"
                  {...bookingDayOfWeek}
                  onChange={(e) => {
                    bookingDayOfWeek.onChange(e);
                    onChangeSchedule(e);
                  }}
                  style={{ textAlign: 'center' }}
                >
                  <option value=""> -- Chọn ngày -- </option>
                  {scheduleTime.map((schedule, idx) => {
                    return (
                      <option key={idx} value={schedule.dayOfWeek}>
                        {moment(schedule.dayOfWeek).format('DD-MM-YYYY')}
                      </option>
                    );
                  })}
                </select>
              </div>
              <br />

              <div>
                <label htmlFor="scheduleTime" className="form-label">
                  Thời gian:
                </label>
                <select
                  className="form-select"
                  aria-label="Default select example"
                  id="scheduleTime"
                  name="scheduleTime"
                  value={formData.scheduleTime.id}
                  {...bookingScheduleTime}
                  onChange={(e) => {
                    bookingScheduleTime.onChange(e);
                    onChangeHandler(e);
                    setFormData({
                      ...formData,
                      scheduleTime: {
                        id: e.target.value,
                      },
                    });
                  }}
                >
                  <option value=""> -- Chọn thời gian -- </option>
                  {hourScheduleTime.map((schedule, idx) => {
                    return (
                      <option key={idx} value={schedule.id}>
                        {moment(schedule.start).format('HH:mm:ss')} -
                        {moment(schedule.end).format('HH:mm:ss')}
                      </option>
                    );
                  })}
                </select>
              </div>
              <br />
            </div>
          </div>
          <br />
          <div>
            {customer.id ? (
              <button type="submit" className="btn btn-primary">
                Đặt lịch
              </button>
            ) : (
              <Link to="/account/userInformation" className="btn btn-primary" onClick={btnAlert}>
                Đặt lịch
              </Link>
            )}
          </div>
        </form>
      </div>
    </div>
  );
}
