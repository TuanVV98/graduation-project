import React from 'react';


export default function ThongKe(){
    return(
        <div className="pt-4 text-center" style={{backgroundColor: 'rgb(246, 249, 249)',height:'900px'}}> 
            <h3 className="">THỐNG KÊ</h3>
            <br/>
            <br/>
            <div className="row mx-5">
            <div className="col-sm-4">
                <div className="card">
                <div className="card-body">
                    <h5 className="card-title">Có 12 lịch đặt bạn đã hủy</h5>
                </div>
                </div>
            </div>
            <div className="col-sm-4">
                <div className="card">
                <div className="card-body">
                    <h5 className="card-title">Có 140 khách hàng đã chọn bạn</h5>
                   </div>
                </div>
            </div>
            <div className="col-sm-4">
                <div className="card">
                <div className="card-body">
                    <h5 className="card-title">Có 128 lịch đặt đã hoàn thành</h5>
                   </div>
                </div>
            </div>
            </div>
            <br/>
            <br/>
            <div className="row mx-5">
            <div className="col-sm-6">
                <div className="card" style={{height: '350px'}}>
                    <h5 className="card-header">Top khách hàng chọn bạn nhiều nhất</h5>
                <div className="card-body">
                    {/* TABLE */}
                    <table className="table table-bordered border border-1 
                        text-center col-sm-8 table-hover mt-1">
                        <thead>
                            <tr>
                                <th scope="col">Tên khách hàng</th>
                                <th scope="col">Số đơn đặt lịch</th>
                            </tr>
                        </thead>

                        <tbody>
                            <tr>
                                <td>Nguyễn Văn Đạt</td>
                                <td>10</td>
                            </tr>
                            <tr>
                                <td>Nguyễn Trung kiên</td>
                                <td>7</td>
                            </tr>
                            <tr>
                                <td>Nguyễn Thành Đạt</td>
                                <td>5</td>
                            </tr>
                            <tr>
                                <td>Nguyễn Chí Thành</td>
                                <td>4</td>
                            </tr>
                        </tbody>
                    </table>
                {/* END TABLE */}
                </div>
                </div>
            </div>
            <div className="col-sm-6">
                <div className="card" style={{height: '350px'}}>
                    <h5 className="card-header">Top bác sĩ nhiều đơn đặt lịch</h5>
                <div className="card-body">
                    {/* TABLE */}
                    <table className="table table-bordered border border-1 
                        text-center col-sm-8 table-hover mt-1">
                        <thead>
                            <tr>
                                <th scope="col">Tên bác sĩ</th>
                                <th scope="col">Số đơn đặt lịch</th>
                            </tr>
                        </thead>

                        <tbody>
                            <tr>
                                <td>Nguyễn Văn Đạt</td>
                                <td>301</td>
                            </tr>
                            <tr>
                                <td>Nguyễn Trung kiên</td>
                                <td>200</td>
                            </tr>
                            <tr>
                                <td>Nguyễn Thành Đạt</td>
                                <td>150</td>
                            </tr>
                            <tr>
                                <td>Nguyễn Chí Thành</td>
                                <td>131</td>
                            </tr>
                        </tbody>
                    </table>
                {/* END TABLE */}
                <p className="text-start">Bạn xếp thứ: 7</p>
                </div>
                </div>
            </div>
            </div>

        </div>
    )

}