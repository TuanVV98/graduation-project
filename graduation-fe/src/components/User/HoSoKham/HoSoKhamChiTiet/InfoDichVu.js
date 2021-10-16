export default function InfoDichVu() {
    return (
        <div className="mb-5">
            <h3>Thông tin dịch vụ</h3>
            <table className="table table-bordered border border-1 
                text-center table-hover">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Tên dịch vụ</th>
                        <th scope="col">Mã giảm giá</th>
                        <th scope="col">Giá tiền</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td>1</td>
                        <td>Nhổ răng</td>
                        <td>VOU2021</td>
                        <td>180.000</td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
}