<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>


<div class="container">
Add todo page for ${name}
<form:form method="post" commandName="todo">
			<fieldset class="form-group">
				<form:hidden path="id"/>  
				<form:label path="desc">Description</form:label>
				<form:input path="desc" type="text" class="form-control" required="required"/>
				<form:errors path="desc" cssClass="text-warning"/>
				
				<form:label path="targetDate">Target Date</form:label>
				<form:input path="targetDate" type="text" class="form-control" required="required"/>
				<form:errors path="targetDate" cssClass="text-warning"/>
				
			</fieldset>
			<button type="submit" class="btn btn-success">Add</button>
		</form:form>
</div>

<%@ include file="common/footer.jspf" %>