import React, { useEffect, useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import axios from 'axios';
import { formatPrice } from '../../../utils/moment-helper';

export default function ShowService() {
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

  const [listData, setListData] = useState([]);
  const [filterData, setFilterData] = useState([]);
  const [page, setPage] = useState(0);
  const [sortValue, setSortValue] = useState('no');
  const [keyword, setKeyWord] = useState('');

  useEffect(() => {
    // const header = `Authorization: Bearer ${token}`;
    const url = 'http://localhost:8080/api/v1/services';
    axios({
      url: url,
      method: 'GET',
    })
      .then((response) => {
        const { data } = response;
        setListData(data.data);
        console.log(data);
        setFilterData(data.data.slice(0, 6));
      })
      .catch((error) => {
        console.log(error, error.response);
      });
  }, []);

  //PAGINATE
  const limit = 6;
  //số bản ghi được phân trang
  const size = Math.ceil((1.0 * listData.length) / limit);

  //PAGINATE
  const btnPrevious = () => {
    if (listData.length > 0 && keyword.trim().length === 0) {
      setPage(page - 1);
      if (page === 0) {
        setPage(size - 1);
      }
    }
  };

  //PAGINATE
  const btnNext = () => {
    if (keyword.trim().length === 0) {
      setPage(page + 1);
      if (page >= size - 1) {
        setPage(0);
      }
    }
  };

  // PAGINATE
  useEffect(() => {
    //page*limit=số bản ghi bỏ qua(vị trí start); page*limit+limit=vị trí end
    //cắt 1 mảng
    if (keyword.trim().length === 0) {
      //sortName
      if (sortValue === 'ascName') {
        setFilterData(
          listData
            .sort((a, b) => a.name.localeCompare(b.name))
            .slice(page * limit, page * limit + limit)
        );
      }
      if (sortValue === 'descName') {
        setFilterData(
          listData
            .sort((a, b) => b.name.localeCompare(a.name))
            .slice(page * limit, page * limit + limit)
        );
      }
      //sortStatus
      if (sortValue === 'ascPrice') {
        setFilterData(
          listData
            .sort((a, b) => parseFloat(a.price) - parseFloat(b.price))
            .slice(page * limit, page * limit + limit)
        );
      }
      if (sortValue === 'descPrice') {
        setFilterData(
          listData
            .sort((a, b) => parseFloat(b.price) - parseFloat(a.price))
            .slice(page * limit, page * limit + limit)
        );
      }

      //default
      if (sortValue === 'no') {
        setFilterData(
          listData
            .sort((a, b) => a.name.localeCompare(b.name))
            .slice(page * limit, page * limit + limit)
        );
      }
    }
  }, [page, listData, sortValue, keyword]);

  //SEARCH
  useEffect(() => {
    setFilterData(
      keyword.trim().length > 0
        ? listData.filter((obj) => {
            return obj.name.toLowerCase().includes(keyword.toLowerCase());
          })
        : listData.slice(page * limit, page * limit + limit).filter((obj) => {
            return obj.name.toLowerCase().includes(keyword.toLowerCase());
          })
    );
  }, [keyword, listData, page]);

  //SORT
  const onSortHandle = (event) => {
    const { value } = event.target;
    setSortValue(value);

    if (keyword.trim().length > 0) {
      //SORT KHI TÌM KIẾM
      //sortName
      if (value === 'ascName') {
        setFilterData(filterData.sort((a, b) => a.name.localeCompare(b.name)));
      }
      if (value === 'descName') {
        setFilterData(filterData.sort((a, b) => b.name.localeCompare(a.name)));
      }
      //sortStatus
      if (value === 'ascPrice') {
        setFilterData(filterData.sort((a, b) => parseFloat(a.price) - parseFloat(b.price)));
      }
      if (value === 'descPrice') {
        setFilterData(filterData.sort((a, b) => parseFloat(b.price) - parseFloat(a.price)));
      }

      //default
      if (value === 'no') {
        setFilterData(filterData.sort((a, b) => a.name.localeCompare(b.name)));
      }
    } else {
      //SORT KHI KHÔNG TÌM KIẾM
      //sortName
      if (value === 'ascName') {
        setFilterData(
          listData
            .sort((a, b) => a.name.localeCompare(b.name))
            .slice(page * limit, page * limit + limit)
        );
      }
      if (value === 'descName') {
        setFilterData(
          listData
            .sort((a, b) => b.name.localeCompare(a.name))
            .slice(page * limit, page * limit + limit)
        );
      }
      //sortStatus
      if (value === 'ascPrice') {
        setFilterData(
          listData
            .sort((a, b) => parseFloat(a.price) - parseFloat(b.price))
            .slice(page * limit, page * limit + limit)
        );
      }
      if (value === 'descPrice') {
        setFilterData(
          listData
            .sort((a, b) => parseFloat(b.price) - parseFloat(a.price))
            .slice(page * limit, page * limit + limit)
        );
      }

      //default
      if (value === 'no') {
        setFilterData(
          listData
            .sort((a, b) => a.name.localeCompare(b.name))
            .slice(page * limit, page * limit + limit)
        );
      }
    }
  };

  const srcUrl = 'http://localhost:8080/api/v1/files/download/image?filename=';

  return (
    <div className="text-center pt-4 mt-5" style={{ minHeight: '700px' }}>
      <h1 className={classes.title}>
        Các dịch vụ của <span className={classes.span}>Smile Dental</span>
      </h1>
      <p>Nụ cười làm tăng giá trị khuôn mặt của bạn!</p>
      <br />
      <br />

      {/* SORT AND SEARCH */}
      <div className="d-flex justify-content-center mt-2">
        <div className="col-sm-6 row container">
          <div className="col-sm-6 mb-3 ">
            {/* SORT */}
            <select
              className="form-select fas fs-5"
              aria-label="Default select example"
              value={sortValue}
              onChange={(event) => onSortHandle(event)}
            >
              <option className="fas" value="no">
                --Sắp xếp--
              </option>

              <option value="ascPrice" className="fas">
                Giá từ thấp đến cao &#xf30c;
              </option>

              <option value="descPrice" className="fas">
                Giá từ cao đến thấp &#xf309;
              </option>

              <option value="ascName" className="fas">
                Tên dịch vụ A-Z &#xf30c;
              </option>

              <option value="descName" className="fas">
                Tên dịch vụ Z-A &#xf309;
              </option>
            </select>
          </div>

          <div className="col-sm-6 mb-2">
            {/* SEARCH */}
            <input
              className="form-control"
              type="search"
              placeholder="Tìm kiếm tên dịch vụ"
              aria-label="Search"
              value={keyword}
              onChange={(event) => setKeyWord(event.target.value)}
            />
          </div>
        </div>
      </div>
      {/* END SORT AND SEARCH */}

      <div id="carouselExampleControlsTinTuc" className="carousel slide" data-bs-ride="carousel">
        <div className="carousel-inner">
          {/* CONTENT 1 */}
          <div className="carousel-item active">
            <div className="card-group mx-5">
              <div className="row row-cols-1 row-cols-md-3 g-4 mx-5">
                {filterData.map((val, idx) => {
                  return (
                    <div className="container" key={idx}>
                      <div className="d-flex justify-content-center">
                        <div className="col" style={{ minWidth: '20rem' }}>
                          <div className="card">
                            <div className="card-body">
                              <img
                                src={srcUrl + val.image}
                                className="card-img-top mb-2 img-fluid"
                                style={{ width: '20rem', height: '7rem' }}
                                alt="..."
                              />
                              <h5 className="card-title">{val.name}</h5>
                              <p className="card-text" style={{ color: '#00bcd5' }}>
                                Giá: {formatPrice(val.price)}
                              </p>
                              <textarea
                                className="form-control"
                                rows="4"
                                readOnly
                                style={{ background: 'none', border: 'none' }}
                                value={val.content}
                              ></textarea>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  );
                })}
              </div>
            </div>
          </div>
          {/* END CONTENT 1 */}

          <button
            className="carousel-control-prev"
            type="button"
            data-bs-target="#carouselExampleControlsTinTuc"
            data-bs-slide="prev"
            onClick={btnPrevious}
          >
            <span
              className="carousel-control-prev-icon btn btn-secondary btn-lg"
              aria-hidden="true"
            ></span>
            <span className="visually-hidden">Previous</span>
          </button>
          <button
            className="carousel-control-next"
            type="button"
            data-bs-target="#carouselExampleControlsTinTuc"
            data-bs-slide="next"
            onClick={btnNext}
          >
            <span
              className="carousel-control-next-icon btn btn-secondary btn-lg"
              aria-hidden="true"
            ></span>
            <span className="visually-hidden">Next</span>
          </button>
        </div>
      </div>
    </div>
  );
}
