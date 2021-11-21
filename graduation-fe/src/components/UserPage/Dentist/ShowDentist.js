//hiển thị thông tin chi tiết của nha sĩ
import React from "react";
import { useState, useEffect } from "react";
import axios from 'axios'
import ImgHeaderDentist from "./ImgHeaderDentist";
function ShowDentist() {



    const [listData, setListData] = useState([]);
    const [filterData, setFilterData] = useState([]);
    const [page, setPage] = useState(0);


    const limit = 3;
    //số bản ghi được phân trang
    const size = Math.ceil(1.0 * listData.length / limit);


    const btnFirst = () => {
        setPage(0);
    }

    const btnPrevious = () => {
        if (listData.length > 0) {
            setPage(page - 1);
            if (page === 0) {
                setPage(size - 1);
            }
        }

    }

    const btnNext = () => {

        setPage(page + 1);
        if (page >= size - 1) {
            setPage(0);

        }

    }

    const btnLast = () => {
        if (listData.length > 0) {
            setPage(size - 1);
        }

    }

    useEffect(() => {
        //page*limit=số bản ghi bỏ qua(vị trí start); page*limit+limit=vị trí end
        //cắt 1 mảng
        setFilterData(listData.slice(page * limit, page * limit + limit));
    }, [page, listData])

    useEffect(() => {

        axios({
            url: 'http://localhost:8080/api/v1/dentists',
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
    }, []);


    const srcUrl = "http://localhost:8080/api/v1/files/download/image?filename=";

    //SORT
    const [sortValue, setSortValue] = useState('no');
    const onSortHandle = (event) => {
        const { value } = event.target;
        setSortValue(value);

        if (value === 'asc') {
            setFilterData(filterData.sort((a, b) => a.fullName.localeCompare(b.fullName)));
        }
        if (value === 'desc') {
            setFilterData(filterData.sort((a, b) => b.fullName.localeCompare(a.fullName)));
        }


    }

    //SEARCH
    const [keyword, setKeyWord] = useState('');
    useEffect(() => {

        setFilterData(
            keyword.trim().length > 0 ?
                listData.filter((obj) => {
                    return obj.fullName.toLowerCase()
                        .includes(keyword.toLowerCase());
                })
                :
                listData.slice(page * limit, page * limit + limit).filter((obj) => {
                    return obj.fullName.toLowerCase()
                        .includes(keyword.toLowerCase());
                })
        );
    }, [keyword, listData, page]);

    return (
        <div>
            <div className="text-center">
                <ImgHeaderDentist />
            </div>

            <div className="col-12 row mt-2">
                <div className="d-flex justify-content-center mt-2">
                    {/* SORT AND SEARCH */}
                    <div className="col-sm-6 row container">

                        <div className="col-sm-6 mb-2 ">
                            {/* SORT */}
                            <select className="form-select" aria-label="Default select example"
                                value={sortValue}
                                onChange={(event) => onSortHandle(event)} >
                                <option value="no">--Sắp xếp tên nha sĩ--</option>
                                <option value="asc">A-Z</option>
                                <option value="desc">Z-A</option>
                            </select>
                        </div>

                        <div className="col-sm-6 mb-2">
                            {/* SEARCH */}
                            <input className="form-control" type="search"
                                placeholder="Tìm kiếm tên nha sĩ" aria-label="Search"
                                value={keyword}
                                onChange={(event) => setKeyWord(event.target.value)}
                            />
                        </div>

                    </div>
                </div>
                {/* END SORT AND SEARCH */}

                {/* CONNTENT1 */}
                {
                    filterData.map((val, idx) => {
                        return (
                            <div className="card mb-3 mt-2 container " style={{ maxWidth: '50rem' }} key={idx}>
                                <div className="row g-0 d-flex justify-content-center">
                                    <div className="col-md-4">
                                        <img src={srcUrl + val.image}
                                            className="img-fluid rounded-start" alt="..." style={{ width: '20rem', height: '20rem' }} />
                                    </div>
                                    <div className="col-md-8">
                                        <div className="card-body">
                                            <h5 className="card-title fs-4">{val.fullName}</h5>
                                            <p className="card-text"><span className="fw-bold">Ngày sinh:</span> {val.birthday}</p>
                                            <p className="card-text"><span className="fw-bold">Thông tin liên hệ:</span> {val.accounts.email}</p>
                                            <p className="card-text fw-bold">Kinh nghiệm:</p>
                                            <p className="card-text">
                                                <textarea className="form-control"
                                                    id="exampleFormControlTextarea1" rows="4"
                                                    readOnly 
                                                    style={{ background: 'none', border: 'none' }}
                                                    value={val.exp}>
                                                </textarea>
                                            </p>
                                            <p className="card-text"><small className="text-muted">Cập nhật {val.updateAt}</small></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        )

                    })
                }

                {/* END CONTENT1 */}



                {/* PAGINATION */}
                <div className="mt-2">
                    <ul className="pagination justify-content-center">
                        <li className="page-item" >
                            <button className="page-link" onClick={btnFirst}>
                                <i className='fas fa-angle-left text-dark'></i>
                            </button>
                        </li>
                        <li className="page-item" >
                            <button className="page-link" onClick={btnPrevious}>
                                <i className='fas fa-angle-double-left text-dark'></i>
                            </button>
                        </li>
                        <li className="page-item">
                            <button className="page-link text-dark">{listData.length > 0 ? page + 1 : 0}/{size}</button>
                        </li>
                        <li className="page-item" >
                            <button className="page-link" onClick={btnNext}>
                                <i className='fas fa-angle-double-right text-dark' ></i>
                            </button>
                        </li>
                        <li className="page-item" >
                            <button className="page-link" onClick={btnLast}>
                                <i className='fas fa-angle-right text-dark'></i>
                            </button>
                        </li>
                    </ul>
                </div>
                {/* END PAGINATION */}

            </div>
        </div>
    )
}
export default ShowDentist;