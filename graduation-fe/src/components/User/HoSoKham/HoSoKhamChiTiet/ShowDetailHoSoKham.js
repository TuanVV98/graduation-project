export default function ShowDetailHoSoKham() {
    return (
        <div>
            <div className="row border border-dark">
                <h3>Chi tiết hồ sơ khám</h3>
                <div className="col-md-4">
                    <div className="form-group mt-3">
                        <label htmlFor="id">Id</label>
                        <input name="id" className="form-control"
                            id="id" autoComplete="off" defaultValue="1" type="text" readOnly />
                    </div>
                    <div className="form-group mt-3">
                        <label htmlFor="date">Ngày đặt lịch</label>
                        <input name="date" className="form-control"
                            id="date" autoComplete="off" defaultValue="07/10/2021"
                            type="text" readOnly />
                    </div>
                </div>

                <div className="col-md-4 mb-3">
                    <div className="form-group mt-3">
                        <label htmlFor="dateK">Ngày khám</label>
                        <input name="dateK" className="form-control" defaultValue="07/10/2021"
                            id="dateK" autoComplete="off" type="text" readOnly />
                    </div>
                    
                    <div className="col-sm-13 row">
                        <div className="form-group mt-3 col-sm-6">
                            <label htmlFor="start">Bắt đầu</label>
                            <input name="start" className="form-control" defaultValue="12:00:00"
                                id="start" autoComplete="off" type="text" readOnly />
                        </div>
                        <div className="form-group mt-3 col-sm-6">
                            <label htmlFor="end">Kết thúc</label>
                            <input name="end" className="form-control" defaultValue="14:00:00"
                                id="end" autoComplete="off" type="text" readOnly />
                        </div>
                    </div>

                </div>

                <div className="form-group mt-3 col-md-4">
                    <label htmlFor="decription">Mô tả</label>
                    <textarea name="decription" className="form-control" defaultValue="nhổ răng"
                        id="decription" autoComplete="off" type="text" rows="4" readOnly />
                </div>
            </div>
        </div>
    )
}