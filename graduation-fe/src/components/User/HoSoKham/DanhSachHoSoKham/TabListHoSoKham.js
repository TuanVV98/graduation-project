export default function TabListHoSoKham() {
    return (
        <div className="container mt-3">
            <div className="row">
                <h3>Danh sách hồ sơ khám</h3>
                
                {/* SORT AND SEARCH */}
                <div className="col-sm-6 row mb-2 offset-6">
                    
                    <div className="col-sm-6">
                        {/* SORT */}
                        <select className="form-select ms-2" aria-label="Default select example">
                            <option defaultChecked>--Sắp xếp--</option>
                            <option value="1">A-Z</option>
                            <option value="2">Z-A</option>
                            <option value="3">Mới nhất</option>
                        </select>
                    </div>

                    <div className="col-sm-6">
                        {/* SEARCH */}
                        <input className="form-control" type="search"
                        placeholder="Tìm kiếm" aria-label="Search" />
                    </div>
                         
                </div>
                {/* END SORT AND SEARCH */}

                {/* TABLE */}
                <table className="table table-bordered border border-1 
                    text-center col-sm-8 table-hover">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Tên bác sĩ</th>
                            <th scope="col">Ngày đặt</th>
                            <th scope="col">Mô tả</th>
                            <th scope="col">Trạng thái</th>
                            <th scope="col">Id thời gian</th>
                            <th scope="col">Hành động</th>
                        </tr>
                    </thead>

                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>Nguyễn Văn Đạt</td>
                            <td>08/10/2021</td>
                            <td>Đau răng</td>
                            <td>Đang chờ xác nhận</td>
                            <td>1</td>
                            <td>
                                <button className="btn btn-info">Xem chi tiết</button>
                            </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Nguyễn Trung kiên</td>
                            <td>09/10/2021</td>
                            <td>Đau răng</td>
                            <td>Xác nhận thành công</td>
                            <td>2</td>
                            <td>
                                <button className="btn btn-info">Xem chi tiết</button>
                            </td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>Nguyễn Thành Đạt</td>
                            <td>10/10/2021</td>
                            <td>Đau răng</td>
                            <td>Đã khám</td>
                            <td>3</td>
                            <td>
                                <button className="btn btn-info">Xem chi tiết</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
                {/* END TABLE */}

                {/* PAGINATION */}
                <div className="mt-2">
                    <ul className="pagination justify-content-center">
                        <li className="page-item" >
                            <button className="page-link">
                                <i className='fas fa-angle-left'></i>
                            </button>
                        </li>
                        <li className="page-item">
                            <button className="page-link">
                                <i className='fas fa-angle-double-left'></i>
                            </button>
                        </li>
                        <li className="page-item">
                            <button className="page-link">2/5</button>
                        </li>
                        <li className="page-item">
                            <button className="page-link">
                                <i className='fas fa-angle-double-right'></i>
                            </button>
                        </li>
                        <li className="page-item">
                            <button className="page-link">
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