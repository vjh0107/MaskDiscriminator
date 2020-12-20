import pyimgur

client_id = 'f93576901fc71a4'
client_secret = '1ceda9777dbf3391fa4226365a3c31d949543881'

im = pyimgur.Imgur(client_id)

uploaded_image = im.upload_image("dataset/User.1.1.jpg", title="adsf")
print(uploaded_image.link)
