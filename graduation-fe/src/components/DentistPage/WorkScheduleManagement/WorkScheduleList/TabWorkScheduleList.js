import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import http from '../../../service/http-common'
import { formatDate } from "../../../../utils/moment-helper";
export default function TabWorkScheduleList({ updateList }) {
    const [listData, setListData] = useState([]);
    const [filterData, setFilterData] = useState([]);
    const [page, setPage] = useState(0);
    const [sortValue, setSortValue] = useState('no');
    const [keyword, setKeyWord] = useState('');




    const limit = 3;
    //số bản ghi được phân trang
    const size = Math.ceil(1.0 * listData.length / limit);


    //PAGINATE
    const btnFirst = () => {
        if (keyword.trim().length === 0) {
            setPage(0);
        }
    }

    const btnPrevious = () => {

        if (listData.length > 0 && keyword.trim().length === 0) {
            setPage(page - 1);
            if (page === 0) {
                setPage(size - 1);
            }
        }

    }

    const btnNext = () => {

        if (keyword.trim().length === 0) {
            setPage(page + 1);
            if (page >= size - 1) {
                setPage(0);

            }
        }


    }

    const btnLast = () => {
        if (listData.length > 0 && keyword.trim().length === 0) {
            setPage(size - 1);
        }

    }

    useEffect(() => {
        //sort theo thời gian đặt lịch mới nhất
        //page*limit=số bản ghi bỏ qua(vị trí start); page*limit+limit=vị trí end
        //cắt 1 mảng
        if (keyword.trim().length === 0) {

            if (sortValue === 'ascName') {
                setFilterData(listData.sort((a, b) =>
                    a.customerProfile.fullname.localeCompare(b.customerProfile.fullname))
                    .slice(page * limit, page * limit + limit)
                );
            }
            if (sortValue === 'descName') {
                setFilterData(listData.sort((a, b) =>
                    b.customerProfile.fullname.localeCompare(a.customerProfile.fullname))
                    .slice(page * limit, page * limit + limit)
                );
            }

            //sort bookingDate
            if (sortValue === 'ascDate') {
                setFilterData(listData.sort((a, b) =>
                    a.bookingDate.localeCompare(b.bookingDate))
                    .slice(page * limit, page * limit + limit)
                );

            }
            if (sortValue === 'descDate' || sortValue === 'no') {
                setFilterData(listData.sort((a, b) =>
                    b.bookingDate.localeCompare(a.bookingDate))
                    .slice(page * limit, page * limit + limit)
                );
            }

            //sortStatus
            if (sortValue === 'ascStatus') {
                setFilterData(listData.sort((a, b) =>
                    a.status - b.status)
                    .slice(page * limit, page * limit + limit)
                );


            }
            if (sortValue === 'descStatus') {
                setFilterData(listData.sort((a, b) =>
                    b.status - a.status)
                    .slice(page * limit, page * limit + limit)
                );
            }
        }




    }, [page, listData, keyword, sortValue])

    //END PAGINATE


    //GET DATA NGƯỜI DÙNG TỪ LOCAL STORAGE(ID,ROLE)
    const [auth, setAuth] = useState({});
    useEffect(() => {
        let json = localStorage.getItem("auth");
        if (json != null && typeof (json) !== 'undefined') {
            setAuth(JSON.parse(json));
        }
    }, [])

    //find ID dentist by accounts_id
    const [dentist, setDentist] = useState({});
    useEffect(() => {
        if (typeof (auth.id) !== 'undefined' && auth.id !== null) {
            http({
                url: '/dentists/accounts/' + auth.id,
                method: 'GET',

            })
                .then((response) => {
                    const { data } = response;
                    setDentist(data.data);
                })
                .catch((error) => {
                    console.log(error, error.response);
                });
        }
    }, [auth]);
    // console.log(customer)


    // find list booking by dentist_id
    // console.log(updateList);
    useEffect(() => {
        if (typeof (dentist.id) !== 'undefined' && dentist.id !== null) {
            http({
                url: '/booking/dentistId/' + dentist.id,
                method: 'GET',

            })
                .then((response) => {
                    const { data } = response;
                    setListData(data.data);
                    setFilterData(data.data.slice(0, 3));
                })
                .catch((error) => {
                    console.log(error, error.response);
                });
        }
    }, [dentist, updateList]);

    //setClick
    const btnClick = (bookingId) => {

        // setShow2("active show");
        // setShow1("");
        sessionStorage.setItem("bookingId", bookingId);
        window.location.reload();


    }


    //SORT

    const onSortHandle = (event) => {
        const { value } = event.target;
        setSortValue(value);

        if (keyword.trim().length > 0) {
            //sort Name dentist
            if (value === 'ascName') {
                setFilterData(filterData.sort((a, b) =>
                    a.customerProfile.fullname.localeCompare(b.customerProfile.fullname)));

            }
            if (value === 'descName') {
                setFilterData(filterData.sort((a, b) =>
                    b.customerProfile.fullname.localeCompare(a.customerProfile.fullname)));
            }

            //sort bookingDate
            if (value === 'ascDate') {
                setFilterData(filterData.sort((a, b) =>
                    a.bookingDate.localeCompare(b.bookingDate)));

            }
            if (value === 'descDate' || value === 'no') {
                setFilterData(filterData.sort((a, b) =>
                    b.bookingDate.localeCompare(a.bookingDate)));
            }

            //sortStatus
            if (value === 'ascStatus') {
                setFilterData(filterData.sort((a, b) =>
                    a.status - b.status));


            }
            if (value === 'descStatus') {
                setFilterData(filterData.sort((a, b) =>
                    b.status - a.status));
            }
        } else {
            //SORT KHI KHÔNG TÌM KIẾM
            if (value === 'ascName') {
                setFilterData(listData.sort((a, b) =>
                    a.customerProfile.fullname.localeCompare(b.customerProfile.fullname))
                    .slice(page * limit, page * limit + limit)
                );
            }
            if (value === 'descName') {
                setFilterData(listData.sort((a, b) =>
                    b.customerProfile.fullname.localeCompare(a.customerProfile.fullname))
                    .slice(page * limit, page * limit + limit)
                );
            }

            //sort bookingDate
            if (value === 'ascDate') {
                setFilterData(listData.sort((a, b) =>
                    a.bookingDate.localeCompare(b.bookingDate))
                    .slice(page * limit, page * limit + limit)
                );

            }
            if (value === 'descDate' || value === 'no') {
                setFilterData(listData.sort((a, b) =>
                    b.bookingDate.localeCompare(a.bookingDate))
                    .slice(page * limit, page * limit + limit)
                );
            }

            //sortStatus
            if (value === 'ascStatus') {
                setFilterData(listData.sort((a, b) =>
                    a.status - b.status)
                    .slice(page * limit, page * limit + limit)
                );


            }
            if (value === 'descStatus') {
                setFilterData(listData.sort((a, b) =>
                    b.status - a.status)
                    .slice(page * limit, page * limit + limit)
                );
            }

        }


    }

    //SEARCH
    useEffect(() => {

        setFilterData(
            keyword.trim().length > 0 ?
                listData.filter((obj) => {
                    return obj.customerProfile.fullname.toLowerCase()
                        .includes(keyword.toLowerCase());
                })
                :
                listData.slice(page * limit, page * limit + limit).filter((obj) => {
                    return obj.customerProfile.fullname.toLowerCase()
                        .includes(keyword.toLowerCase());
                })
        );
    }, [keyword, listData, page]);

    return (
        <div className="container mt-3">
            <div className="row">
                <h3>Danh sách hồ sơ khám</h3>
                <div className="col-sm-6"></div>
                {/* SORT AND SEARCH */}
                <div className="col-sm-6 row ">

                    <div className="col-sm-6 mb-2 ">
                        {/* SORT */}
                        <select className="form-select fas fs-5"
                            aria-label="Default select example"
                            value={sortValue}
                            onChange={(event) => onSortHandle(event)}>
                            <option className="fas" value="no" >--Sắp xếp--</option>

                            <option value="ascDate" className="fas">
                                Ngày đặt lịch &#xf30c;
                            </option>

                            <option value="descDate"
                                className="fas">
                                Ngày đặt lịch &#xf309;
                            </option>

                            <option value="ascStatus"
                                className="fas">Trạng thái &#xf30c;
                            </option>

                            <option value="descStatus"
                                className="fas">Trạng thái &#xf309;
                            </option>

                            <option value="ascName"
                                className="fas">Tên khách hàng A-Z &#xf30c;
                            </option>

                            <option value="descName"
                                className="fas">
                                Tên khách hàng Z-A &#xf309;
                            </option>
                        </select>
                    </div>

                    <div className="col-sm-6 mb-2">
                        {/* SEARCH */}
                        <input className="form-control" type="search"
                            placeholder="Tìm kiếm tên khách hàng" aria-label="Search"
                            value={keyword}
                            onChange={(event) => setKeyWord(event.target.value)} />
                    </div>

                </div>
                {/* END SORT AND SEARCH */}

                {/* TABLE */}
                <div className="container">
                    <div className="table-responsive">
                        <table className="table table-bordered border border-1 
                            text-center col-12 table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">Id</th>
                                    <th scope="col">Tên khách hàng</th>
                                    <th scope="col">Ngày đặt</th>
                                    <th scope="col">Mô tả</th>
                                    <th scope="col">Trạng thái</th>
                                    <th scope="col">Id thời gian</th>
                                    <th scope="col">Hành động</th>
                                </tr>
                            </thead>

                            <tbody>
                                {
                                    filterData.map((val, idx) => {
                                        return (
                                            <tr key={idx}>
                                                <td>{val.id}</td>
                                                <td>{val.customerProfile.fullname}</td>
                                                <td>{formatDate(val.bookingDate)}</td>
                                                <td>{val.description}</td>
                                                <td >
                                                    <select className="form-select text-center"
                                                        value={val.status} disabled
                                                        style={{ background: 'none', border: 'none' }}>

                                                        <option value="0">Chờ xác nhận</option>
                                                        <option value="1">Chờ khám</option>
                                                        <option value="2">Đã khám(hoàn thành)</option>
                                                        <option value="3">Hủy lịch khám</option>
                                                    </select>

                                                </td>
                                                <td>{val.scheduleTime.id}</td>
                                                <td>
                                                    <Link className="btn btn-info"
                                                        to={`/work-schedule`}
                                                        // `&status=`+val.status
                                                        onClick={() => btnClick(val.id)}>Xem chi tiết</Link>
                                                </td>
                                            </tr>
                                        )
                                    })
                                }


                            </tbody>
                        </table>
                        {/* END TABLE */}
                    </div>
                </div>
                {/* PAGINATION */}
                <div className="mt-2">
                    <ul className="pagination justify-content-center">
                        <li className="page-item" >
                            <button className="page-link text-dark" onClick={btnFirst}>
                                <i className='fas fa-angle-left'></i>
                            </button>
                        </li>
                        <li className="page-item" >
                            <button className="page-link text-dark" onClick={btnPrevious}>
                                <i className='fas fa-angle-double-left'></i>
                            </button>
                        </li>
                        <li className="page-item">
                            <button className="page-link text-dark">{listData.length > 0 ? page + 1 : 0}/{size}</button>
                        </li>
                        <li className="page-item" >
                            <button className="page-link text-dark" onClick={btnNext}>
                                <i className='fas fa-angle-double-right' ></i>
                            </button>
                        </li>
                        <li className="page-item" >
                            <button className="page-link text-dark" onClick={btnLast}>
                                <i className='fas fa-angle-right'></i>
                            </button>
                        </li>
                    </ul>
                </div>

                {/* END PAGINATION */}
            </div>
        </div>
    );
}