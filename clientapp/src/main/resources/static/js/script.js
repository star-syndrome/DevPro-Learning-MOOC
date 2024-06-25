function getYouTubeEmbedLink(videoLink) {
	let videoId = "";
	const regex =
		/^.*(?:youtu\.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=)([^#\&\?]*).*/;
	const match = videoLink.match(regex);
	if (match && match[1].length === 11) {
		videoId = match[1];
	}
	return "https://www.youtube.com/embed/" + videoId;
}

$("#videoModal").on("show.bs.modal", function (event) {
	var button = $(event.relatedTarget);
	var videoLink = button.data("video-link");
	var embedLink = getYouTubeEmbedLink(videoLink);
	var modal = $(this);
	modal.find(".modal-body #courseVideo").attr("src", embedLink);
});

$("#videoModal").on("hidden.bs.modal", function () {
	var modal = $(this);
	modal.find(".modal-body #courseVideo").attr("src", "");
});
