	</div>
		<div id="Footer">
			<div id="PoweredBy">
				&nbsp<a href="http://software.csu.edu.cn/">software.csu.edu.cn</a>
			</div>
			<div id="Banner">
				<c:if test="${sessionScope.account != null }">
						<c:if test="${sessionScope.account.bannerOption == true}">
							${sessionScope.account.bannerName}
						</c:if>
				</c:if>
			</div>
		</div>
	</body>
</html>