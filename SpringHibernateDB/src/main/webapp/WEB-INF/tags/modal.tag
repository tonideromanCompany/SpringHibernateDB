<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<!-- Modal HTML -->
<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Delete confirmation</h4>
            </div>
            <div class="modal-body">
                <p>Delete <span id="modalCustomersNumber"></span> customers?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-danger" onClick="check()">Delete Customers</button>
            </div>
        </div>
    </div>
</div>
